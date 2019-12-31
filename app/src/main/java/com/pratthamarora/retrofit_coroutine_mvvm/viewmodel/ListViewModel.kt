package com.pratthamarora.retrofit_coroutine_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pratthamarora.retrofit_coroutine_mvvm.model.Users
import com.pratthamarora.retrofit_coroutine_mvvm.model.UsersService
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {
    private val userService = UsersService.getUsersService()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }
    val users = MutableLiveData<List<Users>>()
    val usersLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchUsers()
    }

    private fun fetchUsers() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = userService.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    users.value = response.body()
                    usersLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun onError(message: String) {
        usersLoadError.value = message
        loading.value = false
    }


}