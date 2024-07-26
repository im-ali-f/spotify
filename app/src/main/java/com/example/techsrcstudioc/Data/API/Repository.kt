package com.example.techsrcstudioc.Data.API


import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.example.techsrcstudioc.Data.Models.searchModel.searchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class Repository {

    suspend fun GetSearchedItems(tokenUser :String , enteredSearch:String , limit:Int  , offset:Int ): Response<searchResponse> {

        return RetrofitInstance.api.GetSearchedItems(tokenUser = tokenUser, enteredSearch =  enteredSearch , limit = limit , offset = offset) //todo limit v aoffset dynamic she
    }

    /*

    suspend fun Login(body:loginBodyModel): Response<loginResponseModel> {

        return RetrofitInstance.api.Login(body)
    }
    suspend fun Signup(body:signupBodyModel): Response<signupResponseModel> {

        return RetrofitInstance.api.Signup(body)
    }

    suspend fun GetUserInfo(tokenUser :String , username:String): Response<userResponseModel> {

        return RetrofitInstance.api.GetUserInfo(tokenUser = tokenUser, username = username)
    }

    suspend fun FollowUser(tokenUser :String , username:String): Response<followResponseModel> {

        return RetrofitInstance.api.FollowUser(tokenUser = tokenUser, username = username)
    }


    suspend fun CreatePost(tokenUser :String , location:RequestBody , text : RequestBody ,photo :MultipartBody.Part ): Response<addPostResponseModel> {

        return RetrofitInstance.api.CreatePost(tokenUser = tokenUser, location =location, text = text, photo = photo )
    }

    suspend fun GetFeed(tokenUser :String , limit:String , offset:String): Response<feedResponseModel> {

        return RetrofitInstance.api.GetFeed(tokenUser = tokenUser, limit = limit, offset = offset)
    }

    suspend fun Like(tokenUser :String , postId:String): Response<likeResponse> {

        return RetrofitInstance.api.Like(tokenUser = tokenUser, postId = postId)
    }

    suspend fun Me(tokenUser :String ): Response<meResponse> {

        return RetrofitInstance.api.Me(tokenUser = tokenUser)
    }

    suspend fun GetProfile(tokenUser :String , username:String): Response<profileInfoResponse> {

        return RetrofitInstance.api.GetProfile(tokenUser = tokenUser, username = username)
    }


     */



    /*
        suspend fun CreateUser(body:UserInfoResponseListItem): Response<UserInfoResponseListItem> {

            return RetrofitInstance.api.CreateUser(body)
        }

         */
}