package com.example.myapplication.presentation.Data

import com.example.myapplication.presentation.view.pages.SolarPanel
import okhttp3.Route
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.solarPanelRoutes() {
    route("/panels") {
        post {
            val panel = call.receive<SolarPanel>()
            transaction {
                SolarPanels.insert {
                    it[groupId] = panel.groupId
                    it[voltage] = panel.voltage
                    it[current] = panel.current
                }
            }
            call.respond(HttpStatusCode.Created)
        }


        get("/same-location") {
            val location = call.request.queryParameters["location"]
            val panels = transaction {
                SolarPanels.select { SolarPanels.location eq location }.map { row ->
                    SolarPanel(
                        row[SolarPanels.id],
                        row[SolarPanels.groupId],
                        row[SolarPanels.voltage],
                        row[SolarPanels.current],
                        row[SolarPanels.temperature],
                        row[SolarPanels.location],
                        row[SolarPanels.status],
                        row[SolarPanels.workerId],
                        row[SolarPanels.installedAt]
                    )
                }
            }
            call.respond(panels)
        }
    }
}