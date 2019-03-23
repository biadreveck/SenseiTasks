package com.hackaton.senseitasks.data

import com.hackaton.senseitasks.domain.TaskPriorityEnum
import com.hackaton.senseitasks.domain.TaskSummary

fun TaskSummaryResult.transformToDomainListOfTaskSummary(): List<TaskSummary> {
    if (tasks.isNullOrEmpty()) return emptyList()
    return tasks.map { aTask ->
        TaskSummary(aTask.id.toString(), aTask.title, TaskPriorityEnum.getByValue(aTask.priority), aTask.isFinished)
    }
}