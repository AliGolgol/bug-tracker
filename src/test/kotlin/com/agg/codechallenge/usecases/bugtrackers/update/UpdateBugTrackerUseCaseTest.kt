package com.agg.codechallenge.usecases.bugtrackers.update

import com.agg.codechallenge.domain.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.helper.DataSample.createUpdateRequest
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class UpdateBugTrackerUseCaseTest {

    @MockBean
    lateinit var repository: H2Repository

    @Autowired
    lateinit var updateBugTrackerUseCase: UpdateBugTrackerUseCase


    @Test
    fun `should throw an exception for given none-existing bugtracker`() {
        val command = UpdateBugTrackerCommand.fromRequest(createUpdateRequest())

        whenever(repository.findById(command.id)).thenReturn(Optional.empty())

        assertThrows<BugTrackerNotFoundException> { updateBugTrackerUseCase.update(command) }
    }
}