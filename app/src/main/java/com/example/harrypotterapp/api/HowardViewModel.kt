package com.example.harrypotterapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HowardViewModel : ViewModel() {
    private var homeRepository:HowardRepository?=null
    var postModelListLiveData : LiveData<List<HowardResponse>>?=null
    var characterDetail : LiveData<List<HowardResponse>>?=null

    init {
        homeRepository = HowardRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllStudents() {
        postModelListLiveData = homeRepository?.fetchAllStudentsList();
    }

    fun fetchAllStaff() {
        postModelListLiveData = homeRepository?.fetchAllStaffList();
    }

    fun fetchCharacterDetail(characterId: String) {
        characterDetail = homeRepository?.fetchCharacterDetail(characterId);
    }
}