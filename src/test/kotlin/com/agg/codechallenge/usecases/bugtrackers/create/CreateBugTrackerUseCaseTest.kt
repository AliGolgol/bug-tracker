package com.agg.codechallenge.usecases.bugtrackers.create

import com.agg.codechallenge.application.ui.requests.CreateBugTrackerRequest
import com.agg.codechallenge.helper.DataSample.createBugTrackerModel
import com.agg.codechallenge.infrastructure.repositories.H2Repository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CreateBugTrackerUseCaseTest {

    @Mock
    lateinit var h2Repository: H2Repository

    @Autowired
    lateinit var createBugTrackerUseCase: CreateBugTrackerUseCase

    @Test
    fun `should create a bug tracker`() {
        val bugTrackerModel = createBugTrackerModel()

        whenever(h2Repository.save(bugTrackerModel)).thenReturn(bugTrackerModel)
        val createBugTrackerRequest = CreateBugTrackerRequest(
            bugTrackerModel.title,
            bugTrackerModel.reporter,
            bugTrackerModel.created,
            bugTrackerModel.status,
            bugTrackerModel.assignee,
            bugTrackerModel.severity
        )
        val actual = createBugTrackerUseCase.create(CreateBugTrackerCommand.fromRequest(createBugTrackerRequest))

        assertThat(actual).isNotNull

    }
}