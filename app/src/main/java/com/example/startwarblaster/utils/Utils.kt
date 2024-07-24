package com.example.startwarblaster.utils

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

val gson = Gson()

inline fun <reified T> getFileFromRaw(resources: Resources, resId: Int): T {
    val jsonString = resources.openRawResource(resId).bufferedReader().use { it.readText() }
    val listOfMatchesDataType: Type = object : TypeToken<T>() {}.type
    return gson.fromJson(jsonString, listOfMatchesDataType)
}