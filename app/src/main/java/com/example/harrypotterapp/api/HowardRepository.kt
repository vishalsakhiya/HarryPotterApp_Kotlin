package com.example.harrypotterapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HowardRepository {
    private var apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun fetchAllHowardList():LiveData<List<HowardResponse>>{
        val data = MutableLiveData<List<HowardResponse>>()
        apiInterface.fetchAllCharacters()?.enqueue(object : Callback<List<HowardResponse>> {
            override fun onFailure(call: Call<List<HowardResponse>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<HowardResponse>>, response: Response<List<HowardResponse>>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })
        return data
    }

    fun fetchAllStudentsList():LiveData<List<HowardResponse>>{
        val data = MutableLiveData<List<HowardResponse>>()
        apiInterface.fetchAllStudents()?.enqueue(object : Callback<List<HowardResponse>> {
            override fun onFailure(call: Call<List<HowardResponse>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<HowardResponse>>, response: Response<List<HowardResponse>>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })
        return data
    }

    fun fetchAllStaffList():LiveData<List<HowardResponse>>{
        val data = MutableLiveData<List<HowardResponse>>()
        apiInterface.fetchAllStaff()?.enqueue(object : Callback<List<HowardResponse>> {
            override fun onFailure(call: Call<List<HowardResponse>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<HowardResponse>>, response: Response<List<HowardResponse>>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })
        return data
    }

    fun fetchCharacterDetail(characterId: String):LiveData<List<HowardResponse>>{
        val data = MutableLiveData<List<HowardResponse>>()
        apiInterface.fetchCharacterDetail(characterId)?.enqueue(object : Callback<List<HowardResponse>> {
            override fun onFailure(call: Call<List<HowardResponse>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<HowardResponse>>, response: Response<List<HowardResponse>>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })
        return data
    }
}