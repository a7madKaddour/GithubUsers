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
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class GetUsersUseCaseTest {
    private lateinit var getUsersUseCase: GetUsersUseCase

    private val usersRepository = mock<IUsersRepository>()

    @Before
    fun init() {
        getUsersUseCase = GetUsersUseCase(usersRepository)
    }

    @Test
    fun testGetUsers(){
        val userEntity: UserEntity = mock()
        val users = listOf(userEntity)

        runBlocking {
            whenever(
                usersRepository.getUsers(any())
            ).thenReturn(flowOf(users))

            val result = getUsersUseCase.execute(
                GetUsersUseCase.GetUsersUserCaseParams(11)
            ).first()

            Assert.assertEquals(1, result.size)
            Assert.assertEquals(userEntity, result[0])

            Mockito.verify(usersRepository).getUsers(11)
            verifyNoMoreInteractions(usersRepository)
        }
    }

    @Test
    fun testGetUsers_emptyResponse(){
        val users = emptyList<UserEntity>()

        runBlocking {
            whenever(
                usersRepository.getUsers(anyOrNull())
            ).thenReturn(flowOf(users))

            val result = getUsersUseCase.execute(
                GetUsersUseCase.GetUsersUserCaseParams()
            ).first()

            Assert.assertEquals(0, result.size)
            Assert.assertEquals(users, result)

            Mockito.verify(usersRepository).getUsers()
            verifyNoMoreInteractions(usersRepository)
        }
    }
}