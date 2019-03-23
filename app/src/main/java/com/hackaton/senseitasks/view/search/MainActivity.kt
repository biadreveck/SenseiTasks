package com.hackaton.senseitasks.view.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.gbazilio.faire.domain.Executors
import com.hackaton.senseitasks.R
import com.hackaton.senseitasks.data.DefaultRemoteRepository
import com.hackaton.senseitasks.data.tasks.TasksApi
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val executors = Executors()

    private val viewModel by lazy {

        val tasksApi = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TasksApi::class.java)

        val repository = DefaultRemoteRepository(tasksApi)
        val useCase = GetTasksListUseCase(repository)
        val viewModelFactory = TasksViewModelFactory(executors, useCase)
        ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.tasks.observe(this, Observer {
            it?.forEach { task ->
                Log.v("TASKS_SENSEI", task.toString())
            }
        })

        viewModel.loadTasks(TaskListFilter())
    }
}
