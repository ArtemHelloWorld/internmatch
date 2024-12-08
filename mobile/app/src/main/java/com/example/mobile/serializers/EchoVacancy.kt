package com.example.mobile.serializers
import kotlinx.serialization.Serializable

@Serializable
class EchoVacancy(
    val id: Int,
    val intern_username: String,
    val resume_link: String? = null,
    val vacancy_meta: Vacancy,
    val text: String,
    val time_created: String,
    val time_updated: String,
    val intern: Int,
    val vacancy: Int,
) {

}