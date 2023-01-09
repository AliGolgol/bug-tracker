package com.agg.codechallenge.usecases.bugtrackers.update

import com.agg.codechallenge.domain.bugtracker.BugTracker
import com.agg.codechallenge.domain.bugtracker.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import org.springframework.stereotype.Service

@Service
class UpdateBugTrackerUseCase(private val repository: H2Repository) {

    fun update(command: UpdateBugTrackerCommand) {
        val findById = repository.findById(command.id)
        when {
            findById.isPresent.not() -> throw BugTrackerNotFoundException()
        }
        updateIt(BugTracker.fromUpdateCommand(command))
    }

    private fun updateIt(bugTracker: BugTracker) {
        repository.save(
            BugTrackerModel(
                bugTracker.id,
                bugTracker.title,
                bugTracker.reporter,
                bugTracker.created,
                bugTracker.status,
                bugTracker.assignee,
                bugTracker.severity
            )
        )
    }
}