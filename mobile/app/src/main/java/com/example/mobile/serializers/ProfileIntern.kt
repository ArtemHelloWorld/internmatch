package com.example.mobile.serializers

import kotlinx.serialization.Serializable

@Serializable
class ProfileIntern (
    val id: Int,
    val skills: List<Skill>,
    val resume_link: String? = null,
    ){
}