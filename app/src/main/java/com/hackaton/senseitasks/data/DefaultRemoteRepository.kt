package com.hackaton.senseitasks.data

import com.hackaton.senseitasks.data.tasks.TasksApi
import com.hackaton.senseitasks.domain.RemoteRepository
import com.hackaton.senseitasks.domain.TaskSummary
import io.reactivex.Observable

class DefaultRemoteRepository(private val tasksApi: TasksApi) : RemoteRepository {

    override fun getTasks() : Observable<List<TaskSummary>> {
        return tasksApi.getTasksList().map { it.transformToDomainListOfTaskSummary() }
    }

}