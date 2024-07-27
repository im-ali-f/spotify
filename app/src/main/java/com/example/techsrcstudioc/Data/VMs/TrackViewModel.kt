package com.example.techsrcstudioc.Data.VMs


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.techsrcstudioc.Data.Models.searchModel.Album
import com.example.techsrcstudioc.Data.Models.searchModel.ArtistX
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalIds
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalUrlsXXX
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track

class TrackViewModel(
    var mainViewModel: MainViewModel,
    var gerenalModel: GeneralViewModel,
    var owner: LifecycleOwner,
    var spotifyAppRemote:SpotifyAppRemote?
) : ViewModel() {
    var spotifyAppRemoteInner = spotifyAppRemote
    var showBottomSheet by mutableStateOf(false)


    var selectedTrack by mutableStateOf(
        Item(
            track_number = 0,
            artists = listOf(),
            name = "",
            id = "",
            album = Album(
                id = "",
                artists = listOf(),
                available_markets = listOf(),
                external_urls = ExternalUrlsXXX(""),
                href = "",
                images = listOf(),
                name = "",
                release_date = "",
                release_date_precision = "",
                total_tracks = 0,
                type = "",
                uri = "",
                album_type = ""
            ),
            disc_number = 0,
            duration_ms = 0,
            explicit = false,
            external_ids = ExternalIds(""),
            external_urls = ExternalUrlsXXX(""),
            href = "",
            is_local = false,
            popularity = 0,
            preview_url = "",
            type = "",
            uri = "",
            available_markets = listOf()


        )
    )

    var muted by mutableStateOf(false)
    var shuffle by mutableStateOf(false)
    var repeatNum by mutableStateOf(1)
    var playing by mutableStateOf(false)



    var voloume by mutableStateOf(0.5f)
    var trackListened by mutableStateOf(0.1f)

    var duration by mutableStateOf( 3620)
    var mins by mutableStateOf(duration / 60)
    var seconds by mutableStateOf(duration % 60)

    fun calculatepassedTime(): String {
        // Ensure trackListened is within the valid range (0.0 to 1.0)
        val trackListenedPercentage = trackListened.coerceIn(0.0f, 1.0f)

        // Calculate the passed time in milliseconds
        val passedTimeMillis = (trackListenedPercentage * duration).toInt()

        // Convert the passed time into minutes and seconds
        val mins = (passedTimeMillis / 60).toInt()
        val seconds = (passedTimeMillis % 60).toInt()

        // Return formatted time as "minutes:seconds"
        return String.format("%d:%02d", mins, seconds)

    }

    fun getArtistString(artistsList: List<ArtistX>): String {
        var artistToReturn = ""
        artistsList.forEach {
            if (artistToReturn != "") {
                artistToReturn += ","
            }
            artistToReturn += it.name
        }
        return artistToReturn
    }

    fun play() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.play(playlistURI)
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("MainActivity", "${track.name} by ${track.artist.name}")
            }
        }
    }

    fun stop() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.pause()
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("MainActivity", "${track.name} by ${track.artist.name}")
            }
        }
    }

    fun resume() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.resume()
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("MainActivity", "${track.name} by ${track.artist.name}")
            }
        }
    }
}