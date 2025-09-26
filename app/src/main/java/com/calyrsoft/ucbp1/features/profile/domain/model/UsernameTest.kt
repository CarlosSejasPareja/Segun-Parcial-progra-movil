package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Assert.*
import org.junit.Test

class UsernameTest {

    @Test fun `valido - entre 3 y 20 sin espacios`() {
        val u = Username("carlos_123")
        assertEquals("carlos_123", u.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - vacio`() { Username("") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - con espacios`() { Username("carlos sejas") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - muy corto`() { Username("ab") }

    @Test(expected = IllegalArgumentException::class)
    fun `invalido - caracteres no permitidos`() { Username("carlos@") }
}
