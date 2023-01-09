package com.agg.codechallenge.usecases.bugtrackers.delete

import com.agg.codechallenge.domain.bugtracker.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteBugTrackerUseCase(private val repository: H2Repository) {

    fun delete(id: UUID) {
        val bugTracker = repository.findById(id)
        when {
            bugTracker.isPresent.not() -> throw BugTrackerNotFoundException()
        }
        repository.delete(bugTracker.get())
    }
}