package com.calyrsoft.ucbp1.features.profile.domain.usecase

import com.calyrsoft.ucbp1.features.profile.domain.model.Email
import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.Username
import com.calyrsoft.ucbp1.features.profile.domain.repository.ILoginRepository

/**
 * Obtiene un usuario por username, y lo mapea a ProfileModel.
 * Nota: ILoginRepository ya existe en tu estructura (login/profile).
 */
class GetProfileUseCase(
    private val repo: ILoginRepository
) {
    suspend operator fun invoke(username: Username): Result<ProfileModel> {
        return try {
            val user = repo.findByName(username.value)
                ?: return Result.failure(NoSuchElementException("Usuario no encontrado"))

            // user: LoginUserModel con name, email, phone?, displayName?
            val model = ProfileModel(
                username = Username(user.name),
                email = Email(user.email),
                phone = user.phone?.let { com.calyrsoft.ucbp1.features.profile.domain.model.PhoneNumber(it) },
                displayName = user.displayName
            )
            Result.success(model)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
