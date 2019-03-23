package com.hackaton.senseitasks.view.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.gbazilio.faire.domain.Executors
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase

class TasksViewModelFactory(private val executors: Executors,
                            private val getTasksListUseCase: GetTasksListUseCase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(executors, getTasksListUseCase) as T
    }

}