package com.example.shemajamebeli_5.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli_5.ApiResponse
import com.example.shemajamebeli_5.Resource
import com.example.shemajamebeli_5.api.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CoursesViewModel : ViewModel() {
    private val _coursesResult = MutableStateFlow<Resource<ApiResponse>?>(null)
    val coursesResult: StateFlow<Resource<ApiResponse>?> get() = _coursesResult

    fun getCourses() {
        viewModelScope.launch {
            _coursesResult.value = Resource.Loading(loading = true)
            try {
                val response = ApiClient.apiService.getCourses()
                if (response.isSuccessful) {
                    _coursesResult.value = Resource.Success(data = response.body())
                } else {
                    _coursesResult.value =
                        Resource.Error(errorMessage = response.errorBody()?.string() ?: "Blank")
                }
            } catch (e: HttpException) {  // Handle HTTP exceptions
                _coursesResult.value = Resource.Error("HTTP Exception: ${e.message}")
            } catch (e: Exception) {
                _coursesResult.value = Resource.Error("An error occurred: ${e.message}")
            } finally {
                _coursesResult.value = Resource.Loading(loading = false)
            }
        }
    }
}
