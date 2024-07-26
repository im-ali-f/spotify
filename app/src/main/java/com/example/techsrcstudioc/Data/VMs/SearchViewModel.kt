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

class SearchViewModel(var mainViewModel: MainViewModel , var gerenalModel: GeneralViewModel ,  var owner: LifecycleOwner ,) : ViewModel() {
    var TAG = "SearchViewModel"
    var enteredSearch by mutableStateOf("")
    var foundedItems by mutableStateOf(listOf<Item>())
    var getNextSearch = ""

    fun GetSearchedItemsFunctionallity() {

        val tokenToSend = "Bearer "+gerenalModel.getData("token", "")
        mainViewModel.GetSearchedItems("$tokenToSend" , enteredSearch , 10, 0)
        mainViewModel.viewModelGetSearchedResponse.observe(owner, Observer { response ->
            if (response.isSuccessful) {

                Log.d("GetSearchedItems --> success", response.body().toString())

                //foundedItems = foundedItems + response.body()!!.tracks.items
                foundedItems =response.body()!!.tracks.items
                getNextSearch = response.body()!!.tracks.next
                Log.d(TAG, "GetSearchedItemsFunctionallity: $getNextSearch")


                mainViewModel.viewModelGetSearchedResponse = MutableLiveData()

            } else {
                Log.d("GetSearchedItems --> error", response.errorBody()?.string() as String)

            }
        })
    }
    //todo fix it
    fun ContinueGetSearchedItemsFunctionallity() {

        val tokenToSend = "Bearer "+gerenalModel.getData("token", "")
        mainViewModel.GetSearchedItems("$tokenToSend" , enteredSearch , 10,10 )
        mainViewModel.viewModelGetSearchedResponse.observe(owner, Observer { response ->
            if (response.isSuccessful) {

                Log.d("GetSearchedItems --> success", response.body().toString())

                //foundedItems = foundedItems + response.body()!!.tracks.items
                foundedItems =response.body()!!.tracks.items
                getNextSearch = response.body()!!.tracks.next
                Log.d(TAG, "GetSearchedItemsFunctionallity: $getNextSearch")


                mainViewModel.viewModelGetSearchedResponse = MutableLiveData()

            } else {
                Log.d("GetSearchedItems --> error", response.errorBody()?.string() as String)

            }
        })
    }

    fun getArtistString(artistsList :List<ArtistX>):String{
        var artistToReturn = ""
        artistsList.forEach{
            if(artistToReturn !=""){
                artistToReturn+=","
            }
            artistToReturn+=it.name
        }
        return artistToReturn
    }
}