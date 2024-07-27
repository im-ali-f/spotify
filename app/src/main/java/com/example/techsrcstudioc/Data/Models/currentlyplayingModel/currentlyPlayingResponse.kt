package com.example.techsrcstudioc.Data.Models.currentlyplayingModel

data class currentlyPlayingResponse(
    val actions: Actions,
    val context: Any,
    val currently_playing_type: String,
    val is_playing: Boolean,
    val item: Item,
    val progress_ms: Int,
    val timestamp: Long
)