package com.hackaton.senseitasks.domain

data class TaskSummary(val id: String,
                       val title: String,
                       val priority: TaskPriorityEnum,
                       val isFinished: Boolean)

data class TaskListFilter(val taskPriority: TaskPriorityEnum = TaskPriorityEnum.ANY)

enum class TaskPriorityEnum {
    PRIORITY_LOW, PRIORITY_NORMAL, PRIORITY_HIGH, PRIORITY_HIGHEST, ANY;

    companion object {
        fun getByValue(value: Int): TaskPriorityEnum = values().first { it.ordinal == value }
    }
}
