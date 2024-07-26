package com.example.techsrcstudioc.Data.VMs


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.techsrcstudioc.Data.Models.searchModel.Album
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalIds
import com.example.techsrcstudioc.Data.Models.searchModel.ExternalUrlsXXX
import com.example.techsrcstudioc.Data.Models.searchModel.Item

class TrackViewModel(
    var mainViewModel: MainViewModel,
    var gerenalModel: GeneralViewModel,
    var owner: LifecycleOwner,
) : ViewModel() {

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
    

}