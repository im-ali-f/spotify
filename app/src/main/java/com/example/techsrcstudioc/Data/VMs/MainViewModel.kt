package com.example.techsrcstudioc.Data.VMs

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techsrcstudioc.Data.API.Repository
import com.example.techsrcstudioc.Data.Models.currentlyplayingModel.currentlyPlayingResponse
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.example.techsrcstudioc.Data.Models.searchModel.searchResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var viewModelGetSearchedResponse: MutableLiveData<Response<searchResponse>> = MutableLiveData()
    fun GetSearchedItems(tokenUser:String , enteredSearch :String,limit:Int,offset:Int) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<searchResponse> = repository.GetSearchedItems(tokenUser = tokenUser , enteredSearch = enteredSearch , limit = limit , offset = offset)
                viewModelGetSearchedResponse.value = response
            } catch (e: Exception) {
                Log.d("GetSearchedItems mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelGetCurrentlyPlaingResponse: MutableLiveData<Response<currentlyPlayingResponse>> = MutableLiveData()
    fun GetCurrentlyPlaing(tokenUser:String , ) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<currentlyPlayingResponse> = repository.GetCurrentlyPlaing(tokenUser = tokenUser)
                viewModelGetCurrentlyPlaingResponse.value = response
            } catch (e: Exception) {
                Log.d("GetSearchedItems mainVM --> Error", "${e.message} ")
            }

        }
    }
    /*

    var viewModelLoginResponse: MutableLiveData<Response<loginResponseModel>> = MutableLiveData()
    fun Login(body:loginBodyModel) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<loginResponseModel> = repository.Login(body)
                viewModelLoginResponse.value = response
            } catch (e: Exception) {
                Log.d("Login mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelSignupResponse: MutableLiveData<Response<signupResponseModel>> = MutableLiveData()
    fun Signup(body:signupBodyModel) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<signupResponseModel> = repository.Signup(body)
                viewModelSignupResponse.value = response
            } catch (e: Exception) {
                Log.d("Signup mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelGetUserInfoResponse: MutableLiveData<Response<userResponseModel>> = MutableLiveData()
    fun GetUserInfo(tokenUser:String , username :String) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<userResponseModel> = repository.GetUserInfo(tokenUser , username =username )
                viewModelGetUserInfoResponse.value = response
            } catch (e: Exception) {
                Log.d("GetUserInfo mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelFollowUserResponse: MutableLiveData<Response<followResponseModel>> = MutableLiveData()
    fun FollowUser(tokenUser:String , username :String) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<followResponseModel> = repository.FollowUser(tokenUser , username =username )
                viewModelFollowUserResponse.value = response
            } catch (e: Exception) {
                Log.d("FollowUser mainVM --> Error", "${e.message} ")
            }

        }
    }


    var viewModelCreatePostResponse: MutableLiveData<Response<addPostResponseModel>> = MutableLiveData()
    fun CreatePost(tokenUser:String, location: RequestBody, text : RequestBody, photo : MultipartBody.Part) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<addPostResponseModel> = repository.CreatePost(tokenUser , location =location, text = text, photo = photo )
                viewModelCreatePostResponse.value = response
            } catch (e: Exception) {
                Log.d("CreateUser mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelGetFeedResponse: MutableLiveData<Response<feedResponseModel>> = MutableLiveData()
    fun GetFeed(tokenUser:String , limit:String,offset:String) {
        viewModelScope.launch { //kotlin coroutines

            try {
                val response: Response<feedResponseModel> = repository.GetFeed(tokenUser , limit, offset )
                viewModelGetFeedResponse.value = response
            } catch (e: Exception) {
                Log.d("GetFeed mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelLikeResponse: MutableLiveData<Response<likeResponse>> = MutableLiveData()
    fun Like(tokenUser:String , postId:String) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<likeResponse> = repository.Like(tokenUser , postId = postId )
                viewModelLikeResponse.value = response
            } catch (e: Exception) {
                Log.d("Like mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelMeResponse: MutableLiveData<Response<meResponse>> = MutableLiveData()
    fun Me(tokenUser:String ) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<meResponse> = repository.Me(tokenUser)
                viewModelMeResponse.value = response
            } catch (e: Exception) {
                Log.d("Me mainVM --> Error", "${e.message} ")
            }

        }
    }

    var viewModelGetProfileResponse: MutableLiveData<Response<profileInfoResponse>> = MutableLiveData()
    fun GetProfile(tokenUser:String , username: String) {
        viewModelScope.launch { //kotlin coroutines
            try {
                val response: Response<profileInfoResponse> = repository.GetProfile(tokenUser , username )
                viewModelGetProfileResponse.value = response
            } catch (e: Exception) {
                Log.d("Like mainVM --> Error", "${e.message} ")
            }

        }
    }



     */


    /*



        var viewModelCreateUserResponse: MutableLiveData<Response<UserInfoResponseListItem>> = MutableLiveData()
        fun CreateUser(body:UserInfoResponseListItem) {
            viewModelScope.launch { //kotlin coroutines
                try {
                    val response: Response<UserInfoResponseListItem> = repository.CreateUser(body)
                    viewModelCreateUserResponse.value = response
                } catch (e: Exception) {
                    Log.d("Create User -->", "${e.message} ")
                }

            }
        }

     */


}