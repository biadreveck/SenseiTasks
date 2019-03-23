package unit.com.hackaton.senseitasks.view

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.gbazilio.faire.domain.Executors
import com.hackaton.senseitasks.domain.RemoteRepository
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.TaskPriorityEnum
import com.hackaton.senseitasks.domain.TaskSummary
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import com.hackaton.senseitasks.view.search.TasksViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var executors: Executors

    @MockK
    private lateinit var repository: RemoteRepository

    private lateinit var getTasksListUseCase : GetTasksListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { executors.io() } returns Schedulers.trampoline()
        every { repository.getTasks() } returns Observable.just(hardcodedSampleTasks())

        getTasksListUseCase = GetTasksListUseCase(repository)
    }

    @Test
    fun `given a valid search filter was used when results return then it should notify observers`() {
        val viewModel = TasksViewModel(executors, getTasksListUseCase)

        viewModel.loadTasks(TaskListFilter())

        assertTrue(viewModel.tasks.value!!.containsAll(
            listOf(
                TaskSummary("1", "Task 1", TaskPriorityEnum.PRIORITY_HIGHEST, false),
                TaskSummary("2", "Task 2", TaskPriorityEnum.PRIORITY_HIGHEST, false)
            )
        ))
    }

    @Test
    fun `given a NORMAL filter was used when results return then it should filter out everything not NORMAL priority`() {
        val viewModel = TasksViewModel(executors, getTasksListUseCase)

        viewModel.loadTasks(TaskListFilter(TaskPriorityEnum.PRIORITY_NORMAL))

        assertTrue(viewModel.tasks.value!!.isEmpty())
    }

    private fun hardcodedSampleTasks() = listOf(
        TaskSummary("1", "Task 1", TaskPriorityEnum.PRIORITY_HIGHEST, false),
        TaskSummary("2", "Task 2", TaskPriorityEnum.PRIORITY_HIGHEST, false)
    )
}
