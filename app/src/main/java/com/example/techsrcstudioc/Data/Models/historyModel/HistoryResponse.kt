package com.example.techsrcstudioc.Data.Models.historyModel

data class HistoryResponse(
    val cursors: Cursors,
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String
)