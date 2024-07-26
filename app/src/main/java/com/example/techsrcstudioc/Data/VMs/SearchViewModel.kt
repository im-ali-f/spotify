package com.example.techsrcstudioc.Data.VMs

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel() : ViewModel() {
    var enteredSearch by mutableStateOf("")
}