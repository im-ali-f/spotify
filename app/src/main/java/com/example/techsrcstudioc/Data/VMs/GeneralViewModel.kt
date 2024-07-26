package com.example.techsrcstudioc.Data.VMs

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Base64
import kotlin.math.exp

class GeneralViewModel(
    var requestPermissionsLauncher: ActivityResultLauncher<Array<String>>,
    var context: Context
) : ViewModel() {
    //permission handling

    /*
        fun checkPermissions() {//todo change permissions that needed

            val requiredPermissions = arrayOf(

                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED


            )

            val permissionsToRequest = requiredPermissions.filter {
                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
            }

            if (permissionsToRequest.isNotEmpty()) {
                requestPermissionsLauncher.launch(permissionsToRequest.toTypedArray())
            }

        }

             */

    //shared preferences
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearData(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun calculateExpire():String{
        val currentTime = System.currentTimeMillis()
        val expireTime = currentTime + (3600 * 1000)
        return expireTime.toString()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun IsExpiredToken(): Boolean {
        Log.d("expired token?", "checkTOKEN: started")

        val token = getData("token", "")
        Log.d("expired token?", "checkTOKEN: $token")
        var expired = false

        if (token == "") {
            expired = true
        } else {
            val expire = getData("expire", "0").toLong() /1000
            val currentTimestamp = System.currentTimeMillis() / 1000
            Log.d("expired token?", "checkTOKEN: ${expire}")
            Log.d("expired token?", "checkTOKEN: $currentTimestamp")

            expired = currentTimestamp > expire

        }


        Log.d("expired token?", "checkTOKEN: $expired")
        return expired

    }

    //home page
    var selectedBottomBar by mutableStateOf(1)

}