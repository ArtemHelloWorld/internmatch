package com.example.mobile.serializers
import kotlinx.serialization.Serializable

@Serializable
class Vacancy(
    val id: Int,
    val employer: String,
    val title: String,
    val description: String,
    val skills: String,
    val salary: Int,
    val duration: Int,
    val hours_per_week: Int,
    val image: String? = null,
) {

}