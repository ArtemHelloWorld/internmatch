package com.example.mobile

import android.content.Context
import android.widget.Toast
import com.example.mobile.serializers.EchoVacancy
import com.example.mobile.serializers.InternInfo
import com.example.mobile.serializers.JWTToken
import com.example.mobile.serializers.Profile
import com.example.mobile.serializers.ProfileEmployer
import com.example.mobile.serializers.ProfileIntern
import com.example.mobile.serializers.Vacancy
import kotlinx.serialization.json.Json
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class Api (
    val context: Context
){
    val BaseUrl: String = "http://192.168.129.41:8000/api/v1"

    fun get(url: String, callback: (String?) -> Unit)  {
        val client = OkHttpClient()

        val builder = Request.Builder().url(url)

        val sharedPreferences = SharedPreferences()
        val accessToken = sharedPreferences.get("access_token", context)

        if (accessToken != null && accessToken != ""){
            builder.header("Authorization", "Bearer ${accessToken}")
        }

        val request = builder.build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    val responseData = it.body?.string()
                    callback(responseData)
                }
            }
        })
    }

    fun post(url: String, formBody: RequestBody, callback: (String?) -> Unit)  {
        val client = OkHttpClient()

        val request = Request.Builder().url(url).post(formBody).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    val responseData = it.body?.string()
                    callback(responseData)
                }
            }
        })
    }

    fun login(username: String, password: String, callback: (JWTToken?) -> Unit) {
        val formBody: RequestBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build()
        post("${BaseUrl}/token/", formBody) { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<JWTToken>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }

    fun getProfile(callback: (Profile?) -> Unit) {
        get("${BaseUrl}/profile/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<Profile>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }
    fun getMyProfileIntern(callback: (ProfileIntern?) -> Unit) {
        get("${BaseUrl}/profile/intern/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<ProfileIntern>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }
    fun getMyProfileEmployer(callback: (ProfileEmployer?) -> Unit) {
        get("${BaseUrl}/profile/employer/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<ProfileEmployer>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }

    fun getInternInfo(id: Int, callback: (InternInfo?) -> Unit) {
        get("${BaseUrl}/intern/${id}/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<InternInfo>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }

    fun getVacanciesList(search: String? = "", callback: (List<Vacancy>) -> Unit) {
        get("${BaseUrl}/vacancy/?search=${search}") { jsonResponse ->
            if (jsonResponse != null) {
                val objs = Json.decodeFromString<List<Vacancy>>(jsonResponse)
                callback(objs)
            }
            else {
                callback(mutableListOf<Vacancy>())
            }
        }
    }

    fun getVacancyDetail(id: Int, callback: (Vacancy?) -> Unit) {
        get("${BaseUrl}/vacancy/${id}/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<Vacancy>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }

    fun getEchoVacanciesList(callback: (List<EchoVacancy>) -> Unit) {
        get("${BaseUrl}/echo-vacancy/") { jsonResponse ->
            if (jsonResponse != null) {
                val objs = Json.decodeFromString<List<EchoVacancy>>(jsonResponse)
                callback(objs)
            }
            else {
                callback(mutableListOf<EchoVacancy>())
            }
        }
    }

    fun getEchoVacancyDetail(id: Int, callback: (EchoVacancy?) -> Unit) {
        get("${BaseUrl}/echo-vacancy/${id}/") { jsonResponse ->
            if (jsonResponse != null) {
                val obj = Json.decodeFromString<EchoVacancy>(jsonResponse)
                callback(obj)
            }
            else {
                callback(null)
            }
        }
    }


}


