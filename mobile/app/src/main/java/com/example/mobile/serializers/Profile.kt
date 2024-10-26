package com.example.mobile.serializers

import kotlinx.serialization.Serializable

@Serializable
class Profile (
    val id: Int,
    val username: String,
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val profile_image: String? = null,
    val user_type: String? = null,
){
}