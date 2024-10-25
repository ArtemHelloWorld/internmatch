package com.example.mobile.serializers

import kotlinx.serialization.Serializable

@Serializable

class JWTToken (
    val access: String,
    val refresh: String,
)
{

}