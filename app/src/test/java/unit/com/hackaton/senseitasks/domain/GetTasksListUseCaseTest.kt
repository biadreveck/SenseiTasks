package com.hackaton.senseitasks.domain

import com.hackaton.senseitasks.domain.tasks.GetTasksListUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetTasksListUseCaseTest {

    @MockK
    private lateinit var repository: RemoteRepository

    private lateinit var sut: GetTasksListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = GetTasksListUseCase(repository)
    }

    @Test
    fun `given a search is requested then make sure it reaches the repository`() {
        every { repository.getTasks() } returns Observable.just(emptyList())

        sut.execute(TaskListFilter())

        verify { repository.getTasks() }
    }
}