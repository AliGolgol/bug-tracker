package com.agg.codechallenge.usecases.bugtrackers.find

import com.agg.codechallenge.domain.exceptions.BugTrackerNotFoundException
import com.agg.codechallenge.helper.DataSample.createBugTrackerModel
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class FindBugTrackerUseCaseTest {

    @MockBean
    lateinit var repository: H2Repository

    @Autowired
    lateinit var findBugTrackerUseCase: FindBugTrackerUseCase

    @Test
    fun `should return a bugtracker for given id`() {
        val bugTrackerModel = createBugTrackerModel()
        whenever(repository.findById(bugTrackerModel.id)).thenReturn(Optional.of(bugTrackerModel))

        val actual = findBugTrackerUseCase.findById(bugTrackerModel.id)

        assertThat(actual).isEqualTo(bugTrackerModel)
    }

    @Test
    fun `should throw an exception for given none-existing id`() {
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(Optional.empty())

        assertThrows<BugTrackerNotFoundException> { findBugTrackerUseCase.findById(id) }
    }

    @Test
    fun `should return bugtrackers`() {
        val bugTrackerModel1 = createBugTrackerModel()
        val bugTrackerModel2 = createBugTrackerModel()
        whenever(repository.findAll()).thenReturn(listOf(bugTrackerModel1, bugTrackerModel2))
        val actual = findBugTrackerUseCase.findAll()
        assertThat(actual.size).isEqualTo(2)
    }
}