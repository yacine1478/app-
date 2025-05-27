package com.example.myapplication.presentation.`Data Models`

import okhttp3.Route

fun Route.workerRoutes() {
    route("/workers") {

        put("/{id}") {
            val workerId = call.parameters["id"]?.toIntOrNull()
            val request = call.receive<WorkerUpdateRequest>()

            transaction {
                Workers.update({ Workers.id eq workerId }) {
                    request.username?.let { username -> it[Workers.username] = username }
                    request.email?.let { email -> it[Workers.email] = email }
                    request.newPassword?.let { pass -> it[password] = hashPassword(pass) }
                }
            }
            call.respond(HttpStatusCode.OK)
        }


        get("/{id}/activities") {
            val workerId = call.parameters["id"]?.toIntOrNull()
            val activities = transaction {
                LogActivities.select { LogActivities.workerId eq workerId }.map { row ->
                    LogActivity(
                        row[LogActivities.id],
                        row[LogActivities.workerId],
                        row[LogActivities.activityType],
                        row[LogActivities.details],
                        row[LogActivities.timestamp]
                    )
                }
            }
            call.respond(activities)
        }
    }
}