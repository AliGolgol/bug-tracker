package com.agg.codechallenge.usecases.bugtrackers.create

import com.agg.codechallenge.domain.bugtracker.BugTracker
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateBugTrackerUseCase(private val repository: H2Repository) {

    fun create(command: CreateBugTrackerCommand): UUID {
        val bugTracker = BugTracker.fromCreateCommand(command)
        val bugTrackerModel = BugTrackerModel(
            bugTracker.id,
            bugTracker.title,
            bugTracker.reporter,
            bugTracker.created,
            bugTracker.status,
            bugTracker.assignee,
            bugTracker.severity
        )
        val result = repository.save(bugTrackerModel)
        return result.id
    }
}