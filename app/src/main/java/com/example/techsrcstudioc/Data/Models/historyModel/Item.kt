package com.example.techsrcstudioc.Data.Models.historyModel

import com.example.techsrcstudioc.Data.Models.searchModel.Item

data class Item(
    val context: Context,
    val played_at: String,
    val track: Item
)