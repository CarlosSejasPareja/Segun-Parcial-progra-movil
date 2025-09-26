package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Test

class PasswordTest {
    @Test fun `valido`() { Password("Abcdefg1") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - corto`() { Password("Abc1") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - falta mayus`() { Password("abcdefg1") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - falta minus`() { Password("ABCDEFG1") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - falta digito`() { Password("Abcdefgh") }
}
