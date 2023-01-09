package com.agg.codechallenge.application.ui.responses

import com.agg.codechallenge.domain.Severity
import com.agg.codechallenge.domain.Status
import java.time.LocalDateTime
import java.util.*

data class BugTrackerResponse(
    val id: UUID,
    val title:String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
)
