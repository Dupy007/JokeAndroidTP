package com.dupy.tp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.await

class JokeViewModel : ViewModel() {
    private val _joke = MutableLiveData<String>()
    val joke: LiveData<String> get() = _joke

    private val apiService = ApiService.create()

    fun fetchJoke() {
        viewModelScope.launch {
            try {
                val response = apiService.getRandomJoke().await()
                println(response)
                _joke.value = response.setup
            } catch (e: Exception) {
                _joke.value = "Failed to fetch joke: ${e.message}"
            }
        }
    }
}
