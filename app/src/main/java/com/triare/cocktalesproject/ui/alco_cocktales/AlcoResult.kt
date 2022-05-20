package com.triare.cocktalesproject.ui.alco_cocktales

import com.triare.cocktalesproject.dvo.AlcoDvo

data class AlcoResult(
    val drinks: List<AlcoDvo>? = null, //dvo
    val error: String? = null,
)
