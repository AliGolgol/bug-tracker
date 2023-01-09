package com.agg.codechallenge.usecases.bugtrackers.update

import com.agg.codechallenge.helper.DataSample.createUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UpdateBugTrackerCommandTest {


    @Test
    fun `should map request to update command`() {
        val request = createUpdateRequest()
        val actual = UpdateBugTrackerCommand.fromRequest(request)

        assertThat(actual.id).isEqualTo(request.id)
        assertThat(actual.title).isEqualTo(request.title)
        assertThat(actual.reporter).isEqualTo(request.reporter)
        assertThat(actual.created).isEqualTo(request.created)
        assertThat(actual.status).isEqualTo(request.status)
        assertThat(actual.assignee).isEqualTo(request.assignee)
        assertThat(actual.severity).isEqualTo(request.severity)
    }
}