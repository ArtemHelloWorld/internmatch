package com.example.mobile.serializers

import kotlinx.serialization.Serializable

@Serializable
class Skill (
    val id: Int,
    val intern: Int,
    val name: String,
    val rate: Int,
){
}