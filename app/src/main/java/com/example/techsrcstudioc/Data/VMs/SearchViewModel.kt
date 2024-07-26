package com.example.techsrcstudioc.Data.VMs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.example.techsrcstudioc.Data.Models.searchModel.ArtistX
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.example.techsrcstudioc.Data.Models.searchModel.searchResponse
import retrofit2.http.Tag

class SearchViewModel(
    var mainViewModel: MainViewModel,
    var gerenalModel: GeneralViewModel,
    var owner: LifecycleOwner,
) : ViewModel() {
    var TAG = "SearchViewModel"
    var enteredSearch by mutableStateOf("")
    var foundedItems by mutableStateOf(listOf<Item>())
    var getNextSearch = ""

    fun GetSearchedItemsFunctionallity() {
        if (enteredSearch != "") {


            val tokenToSend = "Bearer " + gerenalModel.getData("token", "")
            mainViewModel.GetSearchedItems("$tokenToSend", enteredSearch, 10, 0)
            mainViewModel.viewModelGetSearchedResponse.observe(owner, Observer { response ->
                if (response.isSuccessful) {

                    Log.d("GetSearchedItems --> success", response.body().toString())

                    //foundedItems = foundedItems + response.body()!!.tracks.items
                    foundedItems = response.body()!!.tracks.items
                    getNextSearch = response.body()!!.tracks.next



                    mainViewModel.viewModelGetSearchedResponse = MutableLiveData()

                } else {
                    Log.d("GetSearchedItems --> error", response.errorBody()?.string() as String)

                }
            })
        }
        else{
             foundedItems =listOf<Item>()
        }
    }

    //todo fix it
    fun ContinueGetSearchedItemsFunctionallity() {
        if (enteredSearch != "") {

            var list1 = getNextSearch.split("=")
            var limit = list1.get(list1.lastIndex)
            var list2 = list1.get(4).split("&")
            var offset = list2[0]
            Log.d(TAG, "limit: ${limit}")
            Log.d(TAG, "offset: ${offset}")

            val tokenToSend = "Bearer " + gerenalModel.getData("token", "")
            mainViewModel.GetSearchedItems(
                "$tokenToSend",
                enteredSearch,
                limit.toInt(),
                offset.toInt()
            )
            mainViewModel.viewModelGetSearchedResponse.observe(owner, Observer { response ->
                if (response.isSuccessful) {

                    Log.d("GetSearchedItems --> success", response.body().toString())

                    //foundedItems = foundedItems + response.body()!!.tracks.items
                    foundedItems = foundedItems + response.body()!!.tracks.items
                    getNextSearch = response.body()!!.tracks.next
                    Log.d(TAG, "GetSearchedItemsFunctionallity: $getNextSearch")


                    mainViewModel.viewModelGetSearchedResponse = MutableLiveData()

                } else {
                    Log.d("GetSearchedItems --> error", response.errorBody()?.string() as String)

                }
            })
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
}