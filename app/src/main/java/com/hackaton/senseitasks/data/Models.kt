package com.hackaton.senseitasks.data

data class TaskSummary(val id: Int,
                       val title: String,
                       val priority: Int,
                       val isFinished: Boolean)

data class TaskSummaryResult(
    val tasks: List<TaskSummary>
)