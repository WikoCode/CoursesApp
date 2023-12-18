package com.example.shemajamebeli_5.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.example.shemajamebeli_5.ApiResponse
import com.example.shemajamebeli_5.Resource
import com.example.shemajamebeli_5.databinding.FragmentCoursesBinding
import com.example.shemajamebeli_5.viewmodels.CoursesViewModel

class CoursesFragment : BaseFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate) {

    private val viewModel: CoursesViewModel by viewModels()

    override fun setupUI() {
        setupObservers()
        viewModel.getCourses()
    }

    override fun setupListeners() {
    }
    private fun setupObservers() {
        viewModel.coursesResult.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> handleLoading(resource.loading)
                is Resource.Success -> resource.data?.let { handleSuccess(it) }
                is Resource.Error -> handleError(resource.errorMessage)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleSuccess(apiResponse: ApiResponse) {

    }

    private fun handleError(errorMessage: String) {
       
    }


}
