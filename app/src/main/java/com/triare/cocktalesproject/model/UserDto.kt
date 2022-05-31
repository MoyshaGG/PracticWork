package com.triare.cocktalesproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserDto(val name:String,val photo:String): Parcelable
