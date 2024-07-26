package com.example.techsrcstudioc.Data.VMs

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class LoginLogoutViewModel (var context: Context) : ViewModel(){
    fun calURL(){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://accounts.spotify.com/authorize?response_type=token&client_id=36ec84b682f44b089afb62e40fd0e693&redirect_uri=http%3A%2F%2F192.168.1.103%3A3000%2Fapi%2Fauth%2Fspotify%2Fcallback&scope=ugc-image-upload%20user-read-playback-state%20user-modify-playback-state%20user-read-currently-playing%20app-remote-control%20streaming%20playlist-read-private%20playlist-read-collaborative%20playlist-modify-private%20playlist-modify-public%20user-read-playback-position%20user-top-read%20user-read-recently-played%20user-library-modify%20user-library-read%20user-read-email%20user-read-private")        )
        context.startActivity(urlIntent)
    }
}