package com.agg.codechallenge.usecases.bugtrackers.delete

import com.agg.codechallenge.domain.bugtracker.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class DeleteBugTrackerUseCaseTest{

    @MockBean
    lateinit var repository: H2Repository

    @Autowired
    lateinit var deleteBugTrackerUseCase: DeleteBugTrackerUseCase

    @Test
    fun `should throw an exception when none-existing bugtracker`(){
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows<BugTrackerNotFoundException> { deleteBugTrackerUseCase.delete(id) }
    }
}