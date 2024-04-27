package com.example.harrypotterapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("characters")
    fun fetchAllCharacters(): Call<List<HowardResponse>>

    @GET("characters/students")
    fun fetchAllStudents(): Call<List<HowardResponse>>

    @GET("characters/staff")
    fun fetchAllStaff(): Call<List<HowardResponse>>

    @GET("character/{id}")
    fun fetchCharacterDetail(@Path("id") id: String): Call<List<HowardResponse>>
}