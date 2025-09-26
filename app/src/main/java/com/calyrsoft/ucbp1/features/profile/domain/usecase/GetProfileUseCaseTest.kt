package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.Email
import com.calyrsoft.ucbp1.features.profile.domain.model.Username
import com.calyrsoft.ucbp1.features.profile.domain.repository.ILoginRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

// Fake simple para pruebas (evita dependencias a frameworks de mock)
private class FakeLoginRepository(
    private val users: Map<String, TestUser>
) : ILoginRepository {
    data class TestUser(val name: String, val email: String, val phone: String?, val displayName: String?)

    override suspend fun findByName(name: String): TestUser? = users[name]

    // Si tu ILoginRepository tiene otras funciones, decláralas vacías o con TODO() para este test.
    // ...
}

class GetProfileUseCaseTest {

    @Test fun `usuario encontrado retorna ProfileModel`() = runBlocking {
        val repo = object : ILoginRepository {
            override suspend fun findByName(name: String): com.calyrsoft.ucbp1.features.profile.domain.model.LoginUserModel? {
                // si tienes un LoginUserModel real, crea y retorna uno aquí
                return com.calyrsoft.ucbp1.features.profile.domain.model.LoginUserModel(
                    name = "carlos123",
                    email = "carlos@mail.com",
                    phone = "77788899",
                    displayName = "Carlos"
                )
            }
            // Implementa no usados si los hay
        }

        val useCase = GetProfileUseCase(repo)
        val result = useCase(Username("carlos123"))

        assertTrue(result.isSuccess)
        val profile = result.getOrThrow()
        assertEquals("carlos123", profile.username.value)
        assertEquals("carlos@mail.com", profile.email.value)
        assertEquals("77788899", profile.phone?.value)
    }

    @Test fun `usuario no encontrado`() = runBlocking {
        val repo = object : ILoginRepository {
            override suspend fun findByName(name: String) = null
        }
        val useCase = GetProfileUseCase(repo)
        val result = useCase(Username("nadie"))
        assertTrue(result.isFailure)
    }
}
