package com.example.lastApp.models.doctor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Data(
    val about: String,
    val address: String,
    val city: String,
    val id: Int,
    val mobile: String,
    val mobile_tow: String,
    val name: String,
    val photo: String,
    val specialist: String,
    val tell: String,
    val title: String,
    val vacation: String,
) : Parcelable