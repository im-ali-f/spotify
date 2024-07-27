package com.example.techsrcstudioc.Data.API


import com.example.techsrcstudioc.Data.Models.currentlyplayingModel.currentlyPlayingResponse
import com.example.techsrcstudioc.Data.Models.historyModel.HistoryResponse
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.example.techsrcstudioc.Data.Models.searchModel.searchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyAPI {

    @GET("v1/search")
    suspend fun GetSearchedItems(
        @Query("q") enteredSearch: String,
        @Query("type") type: String = "track",
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("include_external") includeExternal: String = "audio",
        @Header("Authorization") tokenUser: String
    ):Response<searchResponse>

    @GET("v1/me/player/currently-playing")
    suspend fun GetCurrentlyTrack(
        @Query("additional_types") additionalTypes: String = "track",
        @Header("Authorization") tokenUser: String
    ):Response<currentlyPlayingResponse>


    @GET("v1/me/player/recently-played")
    suspend fun GetHistoryItems(
        @Query("after") after: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") tokenUser: String
    ):Response<HistoryResponse>

    /*
    @POST("users/login/")
    suspend fun Login(
        @Body loginBodyModel: loginBodyModel
    ): Response<loginResponseModel>

    @POST("users/register/")
    suspend fun Signup(
        @Body userInfoResponseListItem: signupBodyModel
    ):Response<signupResponseModel>

    @GET("users/{username}/")
    suspend fun GetUserInfo(
        @Path("username")username: String,
        @Header("Authorization") tokenUser:String
    ):Response<userResponseModel>

    @GET("users/{username}/follow/")
    suspend fun FollowUser(
        @Path("username")username: String,
        @Header("Authorization") tokenUser:String
    ):Response<followResponseModel>


    @Multipart
    @POST("posts/")
    suspend fun CreatePost(
        @Header("Authorization") tokenUser: String,
        @Part photo: MultipartBody.Part,
        @Part("text") text: RequestBody,
        @Part("location") location: RequestBody
    ): Response<addPostResponseModel>

    @GET("posts/feed/")
    suspend fun GetFeed(
        @Query("limit")limit: String,
        @Query("offset")offset: String,
        @Header("Authorization") tokenUser:String
    ):Response<feedResponseModel>

    @GET("posts/like/{postId}/")
    suspend fun Like(
        @Path("postId")postId: String,
        @Header("Authorization") tokenUser:String
    ):Response<likeResponse>

    @GET("users/me/")
    suspend fun Me(
        @Header("Authorization") tokenUser:String
    ):Response<meResponse>


    @GET("users/{username}/")
    suspend fun GetProfile(
        @Path("username")username: String,
        @Header("Authorization") tokenUser:String
    ):Response<profileInfoResponse>


     */


    /*
    @GET("user")

    suspend fun GetUserList(
    ):Response<UserInfoResponseList>


    @POST("user")
    suspend fun CreateUser(
        @Body userInfoResponseListItem: UserInfoResponseListItem
    ):Response<UserInfoResponseListItem>





    @GET("v1/use")
    suspend fun getUserInfo(
        @Header("Authorization") tokenUser:String
    ):Response<UserModel>

    @PUT("v1/users/person")
    suspend fun putUserInfo(
        @Header("Authorization") tokenUser:String,
        @Body updateUserModel: UpdateUserModel
    ):Response<UpdateUserResponseModel>


     */


}