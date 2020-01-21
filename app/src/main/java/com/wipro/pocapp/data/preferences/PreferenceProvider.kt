package com.doctoremr.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.media.session.MediaSession
import android.util.Log
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.wipro.pocapp.data.models.Token


class PreferenceProvider(context: Context) {

    private val KEY_DOCTOR = "doctor"
    private val KEY_TOKEN = "token"
    private val KEY_SELECTED_PATIENT = "selected_patient"
    private val KEY_HOSPITAL = "key_hospital"
    private val CAMERA_IMAGE_URL = "image_url_uri"

    private val applicationContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(applicationContext)


    fun saveToken(token: Token) {
        val mToken = Gson().toJson(token)
        preferences.edit().putString(KEY_TOKEN, mToken).apply()
    }



    fun getLogin(): Token? {
        val token = preferences.getString(KEY_TOKEN, null)
        token?.let {
            return Gson().fromJson(it,Token::class.java)
        }
        return null
    }

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }



}