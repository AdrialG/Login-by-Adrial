package com.example.notesbyadrialrework.api

import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): String

    @FormUrlEncoded
    @POST("user/get-token")
    suspend fun getToken(): String

    @GET("user/refresh-token")
    suspend fun refreshToken(): String

    @FormUrlEncoded
    @POST("user")
    suspend fun register(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): String

    @FormUrlEncoded
    @POST("user/profile")
    suspend fun getUser(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("photo") photo: String?
    ): String

    @FormUrlEncoded
    @PATCH("user/profile")
    suspend fun updateProfile(
        @Field("name") name: String,
    ): String

    @Multipart
    @PATCH("user/profile")
    suspend fun updateProfileWithPic(
        @Part("name") name: String?,
        @Part photo: MultipartBody.Part?
    ): String

    @FormUrlEncoded
    @POST("note/")
    suspend fun createNote(
        @Field("title") title : String,
        @Field("content") content : String
    ):String

    @GET("note/")
    suspend fun getNote(
        @Query("search") search: String
    ): String

    @FormUrlEncoded
    @PATCH("note/{id}")
    suspend fun updateNote(
        @Path("id") id : String,
        @Field("title") title: String,
        @Field("content") content: String
    ) : String

    @DELETE("note/{id}")
    suspend fun deleteNote(
        @Path("id") id : String,
    ) : String
}