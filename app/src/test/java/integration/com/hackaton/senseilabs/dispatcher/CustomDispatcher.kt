package integration.com.hackaton.senseilabs.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class CustomDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {

        val result = javaClass.getResource("/response.json").readText()

        return when {
            request.path.startsWith("/tasks") -> MockResponse().setBody(result)
            else -> MockResponse().setResponseCode(404)
        }

    }
}