package com.triare.cocktalesproject.ui.alco_cocktales

import com.triare.cocktalesproject.dvo.CocktaleDvo

data class AlcoResult(
    val drinks: List<CocktaleDvo>? = null,
    val error: String? = null,
)
