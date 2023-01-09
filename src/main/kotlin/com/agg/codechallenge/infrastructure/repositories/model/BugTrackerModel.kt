package com.agg.codechallenge.infrastructure.repositories.model

import com.agg.codechallenge.domain.Severity
import com.agg.codechallenge.domain.Status
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "BugTracker")
class BugTrackerModel(
    @Id
    @Type(type = "uuid-char")
    val id: UUID,
    val title: String,
    val reporter: String,
    val created: LocalDateTime,
    val status: Status,
    val assignee: String,
    val severity: Severity
) {
    constructor() : this(UUID.randomUUID(), "", "", LocalDateTime.now(), Status.OPEN, "", Severity.NONE)
}