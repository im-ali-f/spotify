package com.example.techsrcstudioc.Data.VMs


import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techsrcstudioc.Data.Models.searchModel.Album
import com.example.techsrcstudioc.Data.Models.searchModel.ArtistX
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalIds
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalUrlsXXX
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var trackListened by mutableStateOf(0.0f)
    var passedTimeMillisGlobal by mutableStateOf<Long>(0)

    var duration by mutableStateOf( 1)
    var totalTime by mutableStateOf("")

    fun calculatepassedTime(): String {
        // Ensure trackListened is within the valid range (0.0 to 1.0)
        val trackListenedPercentage = trackListened.coerceIn(0.0f, 1.0f)

        // Calculate the passed time in milliseconds
        val passedTimeMillis = (trackListenedPercentage * duration).toInt()
        passedTimeMillisGlobal = passedTimeMillis.toLong()
        Log.d("passedTime", "calculatepassedTime: $passedTimeMillis ")
        // Convert the passed time into minutes and seconds
        val mins = (passedTimeMillis.toInt() / 60000)
        val seconds = (passedTimeMillis.toInt() % 60000)/1000

        // Return formatted time as "minutes:seconds"
        return String.format("%d:%02d", mins, seconds)

    }

    fun seekSlider(){
        viewModelScope.launch {
            /*
            passedTimeMillisGlobal =1
            trackListened = 0.0f

             */
            while (true){
                Log.d("seek slider", "seekSlider: $passedTimeMillisGlobal")
                delay(1000)
                if(playing){
                    passedTimeMillisGlobal +=1000
                    if(duration>1){
                        trackListened = passedTimeMillisGlobal.toFloat()/duration
                    }

                    Log.d("seek slider", "seekSlider: $trackListened f")
                }
            }

        }
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
    fun GetCurrentPlaying(){
        val tokenToSend = "Bearer " + gerenalModel.getData("token", "")
        mainViewModel.GetCurrentlyPlaing(
            "$tokenToSend",)
        mainViewModel.viewModelGetCurrentlyPlaingResponse.observe(owner, Observer { response ->
            if (response.isSuccessful) {

                Log.d("GetCurrentPlaying --> success", response.body().toString())
                response.body()?.let {
                    selectedTrack = response.body()!!.item
                    playing = response.body()!!.is_playing
                    duration = response.body()!!.item.duration_ms
                    calculatepassedTime()
                    val minutes = (duration / 60000)
                    val seconds = (duration % 60000) / 1000
                    totalTime = String.format("%d:%02d", minutes, seconds)


                }


                mainViewModel.viewModelGetCurrentlyPlaingResponse = MutableLiveData()

            } else {
                Log.d("GetCurrentPlaying --> error", response.errorBody()?.string() as String)
                gerenalModel.selectedAlert.value="errorServerapi"
            }
        })
    }
    fun seek(num:Long) {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.seekTo(num)
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track

                Log.d("seek", "${track.name} by ${track.artist.name}")
                Log.d("seek", "${track}")
            }
        }
    }

    fun play() {

        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.play(playlistURI)
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                viewModelScope.launch {
                    delay(2000)
                    GetCurrentPlaying()

                }


                Log.d("play", "${track.name} by ${track.artist.name}")
                Log.d("play", "${track}")
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
                Log.d("stop", "${track.name} by ${track.artist.name}")
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
                Log.d("resume", "${track.name} by ${track.artist.name}")
            }
        }
    }

    fun shuffle() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.setShuffle(shuffle)
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("shuffle", "${track.name} by ${track.artist.name}")
            }
        }
    }

    fun nextTrack() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.skipNext()
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("nextTrack", "${track.name} by ${track.artist.name}")
            }
        }
    }
    fun previousTrack() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.skipPrevious()
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("previousTrack", "${track.name} by ${track.artist.name}")
            }
        }
    }
    fun repeatMode() {
        spotifyAppRemoteInner?.let {
            //val playlistURI = "spotify:album:2sguvaXAzKE5mH8FABsWOi"
            val playlistURI = selectedTrack.uri
            it.playerApi.toggleRepeat()
            it.playerApi.subscribeToPlayerState().setEventCallback { playerState ->
                val track: Track = playerState.track
                Log.d("repeatMode", "${track.name} by ${track.artist.name}")
            }
        }
    }



}