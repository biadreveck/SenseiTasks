package com.hackaton.senseitasks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.github.gbazilio.faire.domain.Executors
import com.hackaton.senseitasks.data.DefaultRemoteRepository
import com.hackaton.senseitasks.data.tasks.TasksApi
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import com.hackaton.senseitasks.view.search.TasksViewModel
import com.hackaton.senseitasks.view.search.TasksViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            val intent = Intent(this, AddTaskActivity::class.java)
//            startActivity(intent)
        }

        viewModel.tasks.observe(this, Observer {
            it?.forEach { task ->
                Log.v("TASKS_SENSEI", task.toString())
            }
            val adapter = TaskListAdapter(this, it.orEmpty())
            list.adapter = adapter
        })

        viewModel.loadTasks(TaskListFilter())

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_logout -> {
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
