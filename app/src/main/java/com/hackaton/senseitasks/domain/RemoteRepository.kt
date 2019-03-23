package com.hackaton.senseitasks.domain

import io.reactivex.Observable

interface RemoteRepository {
    fun getTasks() : Observable<List<TaskSummary>>
}