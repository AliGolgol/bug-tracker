package com.agg.codechallenge.application.ui.mappers

import com.agg.codechallenge.application.ui.responses.BugTrackerResponse
import com.agg.codechallenge.domain.bugtracker.BugTracker
import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel


class BugTrackerResponseMapper {
    companion object {
        fun toResponse(bugTracker: BugTracker) = BugTrackerResponse(
            id = bugTracker.id,
            title = bugTracker.title,
            reporter = bugTracker.reporter,
            created = bugTracker.created,
            status = bugTracker.status,
            assignee = bugTracker.assignee,
            severity = bugTracker.severity
        )

        fun toResponse(bugTracker: BugTrackerModel): BugTrackerResponse {
            return BugTrackerResponse(
                bugTracker.id,
                bugTracker.title,
                bugTracker.reporter,
                bugTracker.created,
                bugTracker.status,
                bugTracker.assignee,
                bugTracker.severity
            )
        }
    }
}
