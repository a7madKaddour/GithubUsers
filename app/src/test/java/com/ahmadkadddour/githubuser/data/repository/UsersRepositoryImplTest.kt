package com.ahmadkadddour.githubuser.data.repository

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.data.model.mapper.UserEntityMapper
import com.ahmadkadddour.githubuser.data.sorce.local.database.service.IUsersLocalService
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.users.IUsersApiService
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
class UsersRepositoryImplTest {
    private lateinit var usersRepositoryImpl: UsersRepositoryImpl

    private val usersApiService = mock<IUsersApiService>()
    private val usersLocalService = mock<IUsersLocalService>()
    private val userEntityMapper = mock<UserEntityMapper>()

    @Before
    fun init() {
        usersRepositoryImpl = UsersRepositoryImpl(
            usersApiService,
            usersLocalService,
            userEntityMapper
        )
    }

    @Test
    fun testGetUsers() {
        val userModel: UserModel = mock()
        val userEntity: UserEntity = mock()
        val userModels = listOf(userModel)
        val userEntities = listOf(userEntity)

        runBlocking {
            whenever(
                usersApiService.getUsers(12)
            ).thenReturn(flowOf(userModels))

            whenever(
                userEntityMapper.map(userModels)
            ).thenReturn(userEntities)

            val result = usersRepositoryImpl.getUsers(12).first()

            Assert.assertEquals(1, result.size)
            Assert.assertEquals(userEntities, result)
            Assert.assertEquals(userEntity, result[0])

            Mockito.verify(usersApiService).getUsers(12)
            verifyNoMoreInteractions(usersApiService)
        }
    }

    @Test
    fun testGetUsers_firstPage() {
        val userModels = listOf<UserModel>()
        val userEntities = listOf<UserEntity>()

        runBlocking {
            whenever(
                usersApiService.getUsers()
            ).thenReturn(flowOf(userModels))

            whenever(
                userEntityMapper.map(userModels)
            ).thenReturn(userEntities)

            usersRepositoryImpl.getUsers().first()

            Mockito.verify(usersApiService).getUsers()
            Mockito.verify(usersLocalService).deleteAllUsers()
            Mockito.verify(usersLocalService).insertUsers(userModels)

            verifyNoMoreInteractions(usersApiService)
            verifyNoMoreInteractions(usersLocalService)
        }
    }

    @Test
    fun testGetUsers_firstPage_error() {
        val userModel: UserModel = mock()
        val userEntity: UserEntity = mock()
        val userModels = listOf(userModel)
        val userEntities = listOf(userEntity)

        runBlocking {
            whenever(
                usersApiService.getUsers()
            ).thenReturn(flow { throw Exception() })

            whenever(
                usersLocalService.getAllUsers()
            ).thenReturn(flowOf(userModels))

            whenever(
                userEntityMapper.map(userModels)
            ).thenReturn(userEntities)

            val result = usersRepositoryImpl.getUsers().first()

            Assert.assertEquals(1, result.size)
            Assert.assertEquals(userEntities, result)
            Assert.assertEquals(userEntity, result[0])

            Mockito.verify(usersApiService).getUsers()
            Mockito.verify(usersLocalService).getAllUsers()

            verifyNoMoreInteractions(usersApiService)
            verifyNoMoreInteractions(usersLocalService)
        }
    }

    @Test(expected = Exception::class)
    fun testGetUsers_error_notInFirstPage() {
        runBlocking {
            whenever(
                usersApiService.getUsers(any())
            ).thenReturn(flow { throw Exception() })

            usersRepositoryImpl.getUsers(12).first()
        }
    }

    @Test
    fun testGetUserByUrl() {
        val userModel: UserModel = mock()
        val userEntity: UserEntity = mock()

        runBlocking {
            whenever(
                usersApiService.getUserByUrl("test")
            ).thenReturn(flowOf(userModel))

            whenever(
                userEntityMapper.map(userModel)
            ).thenReturn(userEntity)

            val result = usersRepositoryImpl.getUserByUrl("test").first()

            Assert.assertEquals(userEntity, result)

            Mockito.verify(usersApiService).getUserByUrl("test")
            Mockito.verify(usersLocalService).insertUser(userModel)

            verifyNoMoreInteractions(usersApiService)
            verifyNoMoreInteractions(usersLocalService)
        }
    }

    @Test
    fun testGetUserByName() {
        val userModel: UserModel = mock()
        val userEntity: UserEntity = mock()

        runBlocking {
            whenever(
                usersApiService.getUserByName("name")
            ).thenReturn(flowOf(userModel))

            whenever(
                userEntityMapper.map(userModel)
            ).thenReturn(userEntity)

            val result = usersRepositoryImpl.getUserByName("name").first()

            Assert.assertEquals(userEntity, result)

            Mockito.verify(usersApiService).getUserByName("name")
            Mockito.verify(usersLocalService).insertUser(userModel)

            verifyNoMoreInteractions(usersApiService)
            verifyNoMoreInteractions(usersLocalService)
        }
    }

    @Test
    fun testGetUserByName_errorHandled() {
        val userModel: UserModel = mock()
        val userEntity: UserEntity = mock()

        runBlocking {
            whenever(
                usersApiService.getUserByName(any())
            ).thenReturn(flow { throw Exception() })

            whenever(
                usersLocalService.getUserByName("name")
            ).thenReturn(flowOf(userModel))

            whenever(
                userEntityMapper.map(userModel)
            ).thenReturn(userEntity)

            val result = usersRepositoryImpl.getUserByName("name").first()

            Assert.assertEquals(userEntity, result)

            Mockito.verify(usersApiService).getUserByName("name")
            Mockito.verify(usersLocalService).getUserByName("name")

            verifyNoMoreInteractions(usersApiService)
            verifyNoMoreInteractions(usersLocalService)
        }
    }

    @Test(expected = IllegalStateException::class)
    fun testGetUserByName_errorNotHandled() {
        runBlocking {
            whenever(
                usersApiService.getUserByName(any())
            ).thenReturn(flow { throw IllegalStateException() })

            whenever(
                usersLocalService.getUserByName(any())
            ).thenReturn(flow { throw IllegalArgumentException() })

            usersRepositoryImpl.getUserByName("").first()
        }
    }
}