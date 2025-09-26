package com.calyrsoft.ucbp1.features.profile.domain.model

@JvmInline
value class Password(val value: String) {
    init {
        require(value.length >= 8) { "Password mínimo 8 caracteres" }
        require(Regex(".*[A-Z].*").containsMatchIn(value)) { "Password requiere una mayúscula" }
        require(Regex(".*[a-z].*").containsMatchIn(value)) { "Password requiere una minúscula" }
        require(Regex(".*\\d.*").containsMatchIn(value))   { "Password requiere un dígito" }
    }
    override fun toString() = "********"
}
