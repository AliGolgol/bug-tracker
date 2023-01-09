package com.agg.codechallenge.domain.exceptions

class BugTrackerNotFoundException (message: String = "bugtracker.not-found") : RuntimeException(message)