package com.agg.codechallenge.usecases.bugtrackers.find

import com.agg.codechallenge.domain.bugtracker.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindBugTrackerUseCase(private val repository: H2Repository) {

    fun findById(id: UUID): BugTrackerModel {
        val bugTracker = repository.findById(id)
        when {
            bugTracker.isPresent.not() -> throw BugTrackerNotFoundException()
        }
        return bugTracker.get()
    }

    fun findAll(): Collection<BugTrackerModel> = repository.findAll()
}