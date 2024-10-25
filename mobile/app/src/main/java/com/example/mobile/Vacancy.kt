package com.example.mobile
import kotlinx.serialization.Serializable

@Serializable
class Vacancy(
    val id: Int,
    val employer: String,
    val title: String,
    val description: String,
    val salary: Int,
    val duration: Int,
    val image: String? = null,
) {

}