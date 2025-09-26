package com.calyrsoft.ucbp1.features.profile.domain.model

@JvmInline
value class Username(val value: String) {
    init {
        require(value.isNotBlank()) { "Username no puede estar vacío" }
        require(!value.contains(" ")) { "Username no puede contener espacios" }
        require(value.length in 3..20) { "Username debe tener entre 3 y 20 caracteres" }
        require(Regex("^[A-Za-z0-9._-]+$").matches(value)) {
            "Username solo puede tener letras, números, '.', '_' o '-'"
        }
    }
    override fun toString() = value
}
