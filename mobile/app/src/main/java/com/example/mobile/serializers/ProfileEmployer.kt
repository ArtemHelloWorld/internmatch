package com.example.mobile.serializers

import kotlinx.serialization.Serializable

@Serializable
class ProfileEmployer (
    val id: Int,
    val vacancies: List<Vacancy>,
    val inn: String? = null,
){
}