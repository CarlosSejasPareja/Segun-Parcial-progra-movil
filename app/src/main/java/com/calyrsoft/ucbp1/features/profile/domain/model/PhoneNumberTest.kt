package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Test

class PhoneNumberTest {
    @Test fun `valido 8 digitos`() { PhoneNumber("77788899") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - corto`() { PhoneNumber("123456") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - largo`() { PhoneNumber("1234567890123456") }
}
