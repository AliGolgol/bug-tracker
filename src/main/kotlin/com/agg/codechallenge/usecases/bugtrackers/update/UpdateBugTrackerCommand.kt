package com.agg.codechallenge.usecases.bugtrackers.update

import com.agg.codechallenge.application.ui.requests.UpdateBugTrackerRequest
import com.agg.codechallenge.domain.bugtracker.Severity
import com.agg.codechallenge.domain.bugtracker.Status
import java.time.LocalDateTime
import java.util.*

data class UpdateBugTrackerCommand(
    val id: UUID,
    val title: String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
) {
    companion object {
        fun fromRequest(request: UpdateBugTrackerRequest): UpdateBugTrackerCommand {
            return UpdateBugTrackerCommand(
                id = request.id,
                title = request.title,
                reporter = request.reporter,
                created = request.created,
                status = request.status,
                assignee = request.assignee,
                severity = request.severity
            )
        }
    }
}
