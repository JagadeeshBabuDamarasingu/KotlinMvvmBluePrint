package com.example.kotlinmvvmblueprint.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmblueprint.base.BaseViewModel
import com.example.kotlinmvvmblueprint.data.DataRepository

class HomeViewModel(dataRepository: DataRepository) : BaseViewModel<HomeScreenNavigator>(dataRepository) {

    private val personsData: MutableLiveData<List<Person>> = MutableLiveData()

    fun getData(): MutableLiveData<List<Person>> = personsData

    init {
        personsData.value = listOf(
            Person("Shiva", "shakthi"),
            Person("lakshmi", "narayana"),
            Person("vani", "hiranyaGarbha")
        )

    }


    data class Person(var firstName: String, var lastName: String)


}