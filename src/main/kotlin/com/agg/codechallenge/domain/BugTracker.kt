package com.agg.codechallenge.domain

import com.agg.codechallenge.usecases.bugtrackers.create.CreateBugTrackerCommand
import com.agg.codechallenge.usecases.bugtrackers.update.UpdateBugTrackerCommand
import java.time.LocalDateTime
import java.util.*

data class BugTracker(
    val id: UUID,
    val title:String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
) {
    companion object {
        fun fromCreateCommand(command: CreateBugTrackerCommand): BugTracker {
            return BugTracker(
                id = UUID.randomUUID(),
                title=command.title,
                reporter = command.reporter,
                created = command.created,
                status = command.status,
                assignee = command.assignee,
                severity = command.severity
            )
        }

        fun fromUpdateCommand(command: UpdateBugTrackerCommand): BugTracker {
            return BugTracker(
                id = command.id,
                title=command.title,
                reporter = command.reporter,
                created = command.created,
                status = command.status,
                assignee = command.assignee,
                severity = command.severity
            )
        }
    }
}