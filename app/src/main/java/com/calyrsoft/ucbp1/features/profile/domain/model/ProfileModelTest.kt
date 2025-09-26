package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Test

class ProfileModelTest {
    @Test fun `con displayName valido`() {
        ProfileModel(
            username = Username("carlos123"),
            email = Email("carlos@mail.com"),
            phone = PhoneNumber("77788899"),
            displayName = "Carlos"
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `displayName invalido cuando es vacio`() {
        ProfileModel(
            username = Username("carlos123"),
            email = Email("carlos@mail.com"),
            phone = null,
            displayName = ""
        )
    }
}
