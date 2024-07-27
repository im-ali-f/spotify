package com.example.techsrcstudioc.Data.VMs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.techsrcstudioc.Data.Models.historyModel.Item
import com.example.techsrcstudioc.Data.Models.searchModel.ArtistX


class HistoryViewModel(
    var mainViewModel: MainViewModel,
    var gerenalModel: GeneralViewModel,
    var owner: LifecycleOwner,
) : ViewModel() {
    var TAG = "HistoryViewModel"
    var after by mutableStateOf("1484811083508")
    var foundedItems by mutableStateOf(listOf<Item>())
    var getNextSearch = ""

    fun GetHistoryItemsFunctionallity() {

            val tokenToSend = "Bearer " + gerenalModel.getData("token", "")
            mainViewModel.GetHistoryItems("$tokenToSend", after, 10)
            mainViewModel.viewModelGetHistoryResponse.observe(owner, Observer { response ->
                if (response.isSuccessful) {

                    Log.d("GetHistoryItemsFunctionallity --> success", response.body().toString())

                    //foundedItems = foundedItems + response.body()!!.tracks.items
                    foundedItems = response.body()!!.items
                    if(response.body()!!.next != null){
                        getNextSearch = response.body()!!.next
                    }


                    Log.d(TAG, "GetHistoryItemsFunctionallity: $getNextSearch")



                    mainViewModel.viewModelGetHistoryResponse = MutableLiveData()

                } else {
                    Log.d("GetHistoryItemsFunctionallity --> error", response.errorBody()?.string() as String)

                }
            })


    }

    //todo fix it
    fun ContinueGetHistoryItemsFunctionallity() {
        var list1 = getNextSearch.split("=")
        var list2 = list1.get(1).split("&")
        var after = list2[0]
        Log.d(TAG, "ContinueGetHistoryItemsFunctionallity: $after")




            val tokenToSend = "Bearer " + gerenalModel.getData("token", "")
            mainViewModel.GetHistoryItems(
                "$tokenToSend",
                after = after,
                limit = 10
            )
            mainViewModel.viewModelGetHistoryResponse.observe(owner, Observer { response ->
                if (response.isSuccessful) {

                    Log.d("ContinueGetHistoryItemsFunctionallity --> success", response.body().toString())

                    //foundedItems = foundedItems + response.body()!!.tracks.items
                    foundedItems = foundedItems + response.body()!!.items
                    if(response.body()!!.next != null){
                        getNextSearch = response.body()!!.next
                    }
                    Log.d(TAG, "ContinueGetHistoryItemsFunctionallity: $getNextSearch")


                    mainViewModel.viewModelGetHistoryResponse = MutableLiveData()

                } else {
                    Log.d("ContinueGetHistoryItemsFunctionallity --> error", response.errorBody()?.string() as String)

                }
            })



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
}