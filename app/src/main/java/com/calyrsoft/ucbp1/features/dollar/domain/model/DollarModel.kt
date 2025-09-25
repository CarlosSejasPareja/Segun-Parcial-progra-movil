package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    var dollarUsdt: String? = null,   // nuevo campo
    var dollarUsdc: String? = null,   // nuevo campo
    var timestamp: Long = 0
)
