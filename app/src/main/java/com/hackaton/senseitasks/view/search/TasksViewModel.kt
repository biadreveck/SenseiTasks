package com.hackaton.senseitasks.view.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.gbazilio.faire.domain.Executors
import com.hackaton.senseitasks.domain.TaskSummary
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import io.reactivex.disposables.CompositeDisposable

class TasksViewModel(private val executors: Executors,
                     private val getTasksList: GetTasksListUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val tasks = MutableLiveData<List<TaskSummary>>()

    fun loadTasks(taskListFilter: TaskListFilter) {
        val disposable = getTasksList.execute(taskListFilter)
            .subscribeOn(executors.io())
            .subscribe { tasks ->
                this.tasks.postValue(tasks)
            }

        disposables.add(disposable)
    }

}