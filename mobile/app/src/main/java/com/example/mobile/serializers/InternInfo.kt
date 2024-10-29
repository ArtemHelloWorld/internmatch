package com.example.mobile.serializers
import kotlinx.serialization.Serializable

@Serializable
class InternInfo (
    val id: Int,
    val user: Profile,
    val skills: List<Skill>
){
}