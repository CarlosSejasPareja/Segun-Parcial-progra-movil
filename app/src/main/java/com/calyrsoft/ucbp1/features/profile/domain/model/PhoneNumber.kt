package com.calyrsoft.ucbp1.features.profile.domain.model

@JvmInline
value class PhoneNumber(val value: String) {
    init {
        val digits = value.filter { it.isDigit() }
        require(digits.length in 7..10) { "Teléfono debe tener 7 a 10 dígitos" }  //incluyendo codigo postal
    }
    override fun toString() = value
}
