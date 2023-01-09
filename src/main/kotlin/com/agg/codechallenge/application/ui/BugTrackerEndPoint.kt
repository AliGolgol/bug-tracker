package com.agg.codechallenge.application.ui

import com.agg.codechallenge.application.ui.mappers.BugTrackerResponseMapper
import com.agg.codechallenge.application.ui.requests.CreateBugTrackerRequest
import com.agg.codechallenge.application.ui.requests.UpdateBugTrackerRequest
import com.agg.codechallenge.application.ui.responses.share.Response
import com.agg.codechallenge.usecases.bugtrackers.create.CreateBugTrackerCommand
import com.agg.codechallenge.usecases.bugtrackers.create.CreateBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.delete.DeleteBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.find.FindBugTrackerUseCase
import com.agg.codechallenge.usecases.bugtrackers.update.UpdateBugTrackerCommand
import com.agg.codechallenge.usecases.bugtrackers.update.UpdateBugTrackerUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/v1/bugtrackers")
class BugTrackerEndPoint(
    private val createBugTrackerUseCase: CreateBugTrackerUseCase,
    private val findBugTrackerUseCase: FindBugTrackerUseCase,
    private val updateBugTrackerUseCase: UpdateBugTrackerUseCase,
    private val deleteBugTrackerUseCase: DeleteBugTrackerUseCase
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun crate(@RequestBody request: CreateBugTrackerRequest) {
        createBugTrackerUseCase.create(CreateBugTrackerCommand.fromRequest(request))
    }

    @GetMapping("/findById")
    fun findById(@RequestParam(required = true) id: UUID) = Response(
        findBugTrackerUseCase.findById(id).let {
            BugTrackerResponseMapper.toResponse(it)
        }
    )

    @GetMapping("/findAll")
    fun findAll() = Response(findBugTrackerUseCase.findAll())

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    fun update(@RequestBody request: UpdateBugTrackerRequest) =
        updateBugTrackerUseCase.update(UpdateBugTrackerCommand.fromRequest(request))

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    fun delete(@RequestParam(required = true) id: UUID) = Response(
        deleteBugTrackerUseCase.delete(id)
    )
}