package com.agg.codechallenge.helper

import com.agg.codechallenge.application.ui.requests.CreateBugTrackerRequest
import com.agg.codechallenge.application.ui.requests.UpdateBugTrackerRequest
import com.agg.codechallenge.domain.Severity
import com.agg.codechallenge.domain.Severity.*
import com.agg.codechallenge.domain.Status
import com.agg.codechallenge.domain.Status.*
import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import java.time.LocalDateTime
import java.util.*

object DataSample {
    fun createUpdateRequest() = UpdateBugTrackerRequest(
        UUID.randomUUID(),
        "login failed when ...",
        "Ali",
        LocalDateTime.now(),
        OPEN,
        "Bahare",
        MEDIUM
    )

    fun createdPayload(): String {
        return """
            {
                "title": "login failed when ...",
                "reporter": "ali",
                "created": "2018-03-29T13:34:00",
                "status": "OPEN",
                "assignee": "bahare",
                "severity": "NONE"
            }""".trimMargin()
    }

    fun updatedPayload(): String {
        return """
            {
                "id": "b64dcfa5-ae59-42e2-b33e-d28bc2624144",
                "title": "login failed when ...",
                "reporter": "anonymous",
                "created": "2018-03-29T13:34:00",
                "status": "OPEN",
                "assignee": "ali",
                "severity": "NONE"
            }""".trimMargin()
    }

    fun createBugTrackerRequest() = CreateBugTrackerRequest(
        "login failed when ...",
        "Ali",
        LocalDateTime.now(),
        OPEN,
        "Bahare",
        MEDIUM
    )

    fun createBugTrackerModel() =
        BugTrackerModel(
            UUID.randomUUID(),
            "login failed when ...",
            "Ali",
            LocalDateTime.now(),
            OPEN,
            "Bahar",
            MEDIUM
        )
}