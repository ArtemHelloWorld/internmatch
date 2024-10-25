package com.example.mobile

import android.widget.Toast
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Api {
    fun performGetRequest(url: String, callback: (String?) -> Unit)  {
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

    fun getVacanciesList(callback: (List<Vacancy>) -> Unit) {
        performGetRequest("http://172.28.125.124:8000/api/v1/vacancy/") { jsonResponse ->
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
        performGetRequest("http://172.28.125.124:8000/api/v1/vacancy/${id}/") { jsonResponse ->
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


