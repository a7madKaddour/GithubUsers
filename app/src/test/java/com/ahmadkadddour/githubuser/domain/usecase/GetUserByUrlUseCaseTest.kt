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
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetUserByUrlUseCaseTest {
    private lateinit var getUserByUseCase: GetUserByUrlUseCase

    private val usersRepository = mock<IUsersRepository>()

    @Before
    fun init() {
        getUserByUseCase = GetUserByUrlUseCase(usersRepository)
    }


    @Test
    fun testGetUserByUrl(){
        val userEntity: UserEntity = mock()

        runBlocking {
            whenever(
                usersRepository.getUserByUrl(any())
            ).thenReturn(flowOf(userEntity))

            val result = getUserByUseCase.execute(
                GetUserByUrlUseCase.GetUserByUrlUserCaseParams("test")
            ).first()

            Assert.assertEquals(userEntity, result)

            verify(usersRepository).getUserByUrl("test")
            verifyNoMoreInteractions(usersRepository)
        }
    }
}