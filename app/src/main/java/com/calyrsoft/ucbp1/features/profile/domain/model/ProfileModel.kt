package com.calyrsoft.ucbp1.features.profile.domain.model

data class ProfileModel(
    val username: Username,
    val email: Email,
    val phone: PhoneNumber?,
    val displayName: String? = null
) {
    init {
        displayName?.let {
            require(it.isNotBlank()) { "displayName no puede ser vacío si se proporciona" }
        }
    }
}
