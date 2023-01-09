package com.agg.codechallenge.infrastructure.repositories

import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface H2Repository : JpaRepository<BugTrackerModel, UUID>