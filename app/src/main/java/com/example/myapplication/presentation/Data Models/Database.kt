package com.example.myapplication.presentation.Data

import android.icu.number.Precision.integer
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.presentation.Data.SolarPanels.current
import com.example.myapplication.presentation.Data.SolarPanels.groupId
import com.example.myapplication.presentation.Data.SolarPanels.status
import com.example.myapplication.presentation.Data.SolarPanels.voltage
import com.example.myapplication.presentation.view.pages.SolarPanel
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.name
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
object DatabaseFactory {
    fun init() {
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val jdbcURL = "jdbc:mysql://localhost:3306/myapp_db?useSSL=false&serverTimezone=UTC"
        val database = Database.connect(
            url = jdbcURL,
            driver = driverClassName,
            user = "root",
            password = ""
        )

        transaction {
            SchemaUtils.create(Workers, SolarPanels, LogActivities)
        }
    }
}

// Workers Table
object Workers : Table("workers") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50)
    val email = varchar("email", 100)
    val passwordHash = varchar("password_hash", 100)
    val createdAt = varchar("created_at", 50)

    override val primaryKey = PrimaryKey(id)
}

// SolarPanels Table
object SolarPanels : Table("solar_panels") {
    val id = integer("id").autoIncrement()
    val groupId = varchar("group_id", 50)
    val voltage = double("voltage")
    val current = double("current")
    val temperature = double("temperature")
    val location = varchar("location", 100)
    val status = varchar("status", 20)
    val workerId = integer("worker_id").references(Workers.id)
    val installedAt = varchar("installed_at", 50)

    override val primaryKey = PrimaryKey(id)
}

// LogActivities Table
object LogActivities : Table("log_activities") {
    val id = integer("id").autoIncrement()
    val workerId = integer("worker_id").references(Workers.id)
    val activityType = varchar("activity_type", 50)
    val details = text("details")
    val timestamp = varchar("timestamp", 50)

    override val primaryKey = PrimaryKey(id)


    class WorkerDAO {
        @RequiresApi(Build.VERSION_CODES.O)
        fun createWorker(username: String, email: String, passwordHash: String): Int {
            return transaction {
                Workers.insert {
                    it[Workers.username] = username
                    it[Workers.email] = email
                    it[Workers.passwordHash] = passwordHash
                    it[createdAt] = LocalDateTime.now().toString()
                } get Workers.id
            }
        }

        fun updateWorker(workerId: Int, updateRequest: WorkerUpdateRequest) {
            transaction {
                Workers.update({ Workers.id eq workerId }) {
                    updateRequest.username?.let { username -> it[Workers.username] = username }
                    updateRequest.email?.let { email -> it[Workers.email] = email }
                    updateRequest.newPassword?.let { pass -> it[passwordHash] = hashPassword(pass) }
                }
            }
        }

        private fun hashPassword(password: String): String {

            return password
        }
    }

    class SolarPanelDAO {
        @RequiresApi(Build.VERSION_CODES.O)
        fun createPanel(panel: SolarPanel): Int {
            return transaction {
                SolarPanels.insert {

                    it[voltage] = panel.voltage
                    it[current] = panel.current
                    it[temperature] = panel.temperature

                    it[status] = panel.status
                    it[workerId] = panel.id
                    it[installedAt] = LocalDateTime.now().toString()
                } get SolarPanels.id
            }
        }

        fun getPanelsByLocation(location: String): List<SolarPanel> {
            return transaction {
                SolarPanels.select { SolarPanels.location eq location }.map { row ->
                    SolarPanel(
                        id = row[SolarPanels.id],

                        voltage = row[voltage],
                        current = row[current],
address = row[SolarPanels. location],
                        status = row[status],
                        temperature = row[SolarPanels.temperature]
                    )

                }
            }
        }

        class LogActivityDAO {
            @RequiresApi(Build.VERSION_CODES.O)
            fun logActivity(workerId: Int, activityType: String, details: String) {
                transaction {
                    LogActivities.insert {
                        it[LogActivities.workerId] = workerId
                        it[LogActivities.activityType] = activityType
                        it[LogActivities.details] = details
                        it[timestamp] = LocalDateTime.now().toString()
                    }
                }
            }
        }
    }
}