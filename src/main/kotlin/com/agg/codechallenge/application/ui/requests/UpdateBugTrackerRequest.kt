package com.agg.codechallenge.application.ui.requests

import com.agg.codechallenge.domain.bugtracker.Severity
import com.agg.codechallenge.domain.bugtracker.Status
import java.time.LocalDateTime
import java.util.*

class UpdateBugTrackerRequest(
    val id: UUID,
    val title: String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
)
