package com.hackaton.senseitasks.domain.tasks

import com.hackaton.senseitasks.domain.RemoteRepository
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.TaskPriorityEnum
import com.hackaton.senseitasks.domain.TaskSummary
import io.reactivex.Observable

class GetTasksListUseCase(private val repository: RemoteRepository) {

    fun execute(taskListFilter: TaskListFilter): Observable<List<TaskSummary>> {
        return repository.getTasks().map { allTasks ->
            allTasks.filter { it.priority == taskListFilter.taskPriority || taskListFilter.taskPriority == TaskPriorityEnum.ANY}
        }
    }

}