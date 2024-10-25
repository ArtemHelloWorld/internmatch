package com.example.mobile

import com.example.mobile.serializers.JWTToken
import com.example.mobile.serializers.Vacancy
import kotlinx.serialization.json.Json
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class Api {
    val BaseUrl: String = "http://192.168.59.41:8000/api/v1"

    fun get(url: String, callback: (String?) -> Unit)  {
        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

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

    fun getVacanciesList(callback: (List<Vacancy>) -> Unit) {
        get("${BaseUrl}/vacancy/") { jsonResponse ->
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


}


