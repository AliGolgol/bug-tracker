package com.agg.codechallenge.usecases.bugtrackers.create

import com.agg.codechallenge.domain.Severity.MEDIUM
import com.agg.codechallenge.domain.Status.OPEN
import com.agg.codechallenge.helper.DataSample.createBugTrackerRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CreateBugTrackerCommandTest {

    @Test
    fun `should return a CreateBugTrackerCommand`() {
        val command = CreateBugTrackerCommand.fromRequest(
            createBugTrackerRequest()
        )


        assertThat(command.assignee).isEqualTo("Bahare")
        assertThat(command.reporter).isEqualTo("Ali")
        assertThat(command.status).isEqualTo(OPEN)
        assertThat(command.severity).isEqualTo(MEDIUM)
    }
}