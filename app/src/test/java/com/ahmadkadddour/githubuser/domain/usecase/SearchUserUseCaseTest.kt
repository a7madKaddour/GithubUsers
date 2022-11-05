package com.ahmadkadddour.githubuser.domain.usecase

import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.repository.IUsersRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchUserUseCaseTest {
    private lateinit var searchUserUseCase: SearchUserUseCase

    private val usersRepository = mock<IUsersRepository>()

    @Before
    fun init() {
        searchUserUseCase = SearchUserUseCase(usersRepository)
    }

    @Test
    fun testGetUserByName(){
        val userEntity: UserEntity = mock()

        runBlocking {
            whenever(
                usersRepository.getUserByName(any())
            ).thenReturn(flowOf(userEntity))

            val result = searchUserUseCase.execute(
                SearchUserUseCase.SearchUserUseCaseParams("search")
            ).first()

            Assert.assertEquals(userEntity, result)

            Mockito.verify(usersRepository).getUserByName("search")
            verifyNoMoreInteractions(usersRepository)
        }
    }
}