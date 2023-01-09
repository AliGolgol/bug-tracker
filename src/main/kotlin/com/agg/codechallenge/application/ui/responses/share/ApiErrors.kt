package com.agg.codechallenge.application.ui.responses.share

data class ApiErrors(val errors: List<ApiError>)

data class ApiError(val id: String, val status: String, val title: String, val detail: String?)
