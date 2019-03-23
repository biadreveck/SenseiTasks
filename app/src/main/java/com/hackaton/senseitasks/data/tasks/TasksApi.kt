package com.hackaton.senseitasks.data.tasks

import com.hackaton.senseitasks.data.TaskSummaryResult
import io.reactivex.Observable
import retrofit2.http.POST

interface TasksApi {

    @POST("tasks")
    fun getTasksList() : Observable<TaskSummaryResult>

}