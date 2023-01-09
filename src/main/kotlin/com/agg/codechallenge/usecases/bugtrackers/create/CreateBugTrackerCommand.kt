package com.agg.codechallenge.usecases.bugtrackers.create

import com.agg.codechallenge.application.ui.requests.CreateBugTrackerRequest
import com.agg.codechallenge.domain.bugtracker.Severity
import com.agg.codechallenge.domain.bugtracker.Status
import java.time.LocalDateTime

data class CreateBugTrackerCommand(
    val title:String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
) {
    companion object {
        fun fromRequest(request: CreateBugTrackerRequest): CreateBugTrackerCommand {
            return CreateBugTrackerCommand(
                title=request.title,
                reporter = request.reporter,
                created = request.created,
                status = request.status,
                assignee = request.assignee,
                severity = request.severity
            )
        }
    }
}
