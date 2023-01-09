package com.agg.codechallenge.domain.bugtracker.exceptions

class BugTrackerNotFoundException (message: String = "bugtracker.not-found") : RuntimeException(message)