package integration.com.hackaton.senseilabs

import com.hackaton.senseitasks.data.DefaultRemoteRepository
import com.hackaton.senseitasks.data.tasks.TasksApi
import com.hackaton.senseitasks.domain.TaskListFilter
import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import integration.com.hackaton.senseilabs.dispatcher.CustomDispatcher
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GetTaskListScenario {

    @Test
    fun `given a valid search when results are returned then a list of tasks must exist`() {
        val mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(CustomDispatcher())
        mockWebServer.start()

        val url = mockWebServer.url("/").toString()

        val sut = buildUseCase(url)

        val filter = TaskListFilter()
        sut.execute(filter)
            .subscribeOn(Schedulers.trampoline())
            .subscribe {
            // assert every single maker
        }
    }

    private fun buildUseCase(baseApiUrl: String) : GetTasksListUseCase{
        val api = retrofit(baseApiUrl).create(TasksApi::class.java)
        val repository = DefaultRemoteRepository(api)
        return GetTasksListUseCase(repository)
    }

    private fun retrofit(url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

}