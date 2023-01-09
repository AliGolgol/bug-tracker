package com.agg.codechallenge.application.ui

import com.agg.codechallenge.infrastructure.repositories.model.BugTrackerModel
import com.agg.codechallenge.usecases.bugtrackers.create.CreateBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.delete.DeleteBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.find.FindBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.update.UpdateBugTrackerCommand
import com.agg.codechallenge.usecases.bugtrackers.update.UpdateBugTrackerUseCase
import com.agg.codechallenge.helper.DataSample.createUpdateRequest
import com.agg.codechallenge.helper.DataSample.createdPayload
import com.agg.codechallenge.helper.DataSample.updatedPayload
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@WebMvcTest(BugTrackerEndPoint::class)
internal class BugTrackerEndPointTest {
    private val BASE_URI: String = "/api/v1/bugtrackers"

    @MockBean
    lateinit var createBugTrackerUseCase: CreateBugTrackerUseCase

    @MockBean
    lateinit var deleteBugTrackerUseCase: DeleteBugTrackerUseCase

    @MockBean
    lateinit var findBugTrackerUseCase: FindBugTrackerUseCase

    @MockBean
    lateinit var updateBugTrackerUseCase: UpdateBugTrackerUseCase

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }


    @Test
    fun `create should return 201 status when a bug tracker is created`() {
        whenever(createBugTrackerUseCase.create(any())).thenReturn(UUID.randomUUID())
        mvc.perform(
            MockMvcRequestBuilders.post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createdPayload())
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `finds should return 200 with the existing bug tracker`() {
        val bugTracker = BugTrackerModel()
        whenever(findBugTrackerUseCase.findAll()).thenReturn(listOf(bugTracker))
        whenever(findBugTrackerUseCase.findById(bugTracker.id)).thenReturn(bugTracker)

        mvc.perform(
            MockMvcRequestBuilders.get("$BASE_URI/findAll")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.OK.value()))

        mvc.perform(
            MockMvcRequestBuilders.get(BASE_URI + "/findById?id=${bugTracker.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.OK.value()))
    }

    @Test
    fun `delete should return 204 with the existing bug tracker`() {
        val id = UUID.randomUUID()
        doNothing().whenever(deleteBugTrackerUseCase).delete(id)

        mvc
            .perform(
                MockMvcRequestBuilders.delete(BASE_URI + "?id=${id}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
            ).andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.NO_CONTENT.value()))
    }

    @Test
    fun `Put should return 204 status when an existing bug tracker is updated`() {
        val request = createUpdateRequest()
        doNothing().whenever(updateBugTrackerUseCase).update(UpdateBugTrackerCommand.fromRequest(request))

        mvc
            .perform(
                MockMvcRequestBuilders.put(BASE_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedPayload())
                    .characterEncoding("utf-8")
            ).andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.NO_CONTENT.value()))

    }
}