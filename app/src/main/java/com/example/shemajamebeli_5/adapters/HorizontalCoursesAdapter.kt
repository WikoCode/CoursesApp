package com.example.shemajamebeli_5.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli_5.NewCourse
import com.example.shemajamebeli_5.databinding.ItemNewCourseBinding

class HorizontalCoursesAdapter : ListAdapter<NewCourse, HorizontalCoursesAdapter.CourseViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewCourseBinding.inflate(inflater, parent, false)
        return CourseViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    class CourseViewHolder(private val binding: ItemNewCourseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: NewCourse) {
            binding.tvTitle.text = course.title
            binding.tvDuration.text = course.duration
            binding.tvQuestion.text = course.question
            binding.ivStart.setColorFilter(Color.parseColor(course.mainColor))
            binding.ivBackground.setColorFilter(Color.parseColor(course.mainColor))

        }
    }

    companion object {
        private val CourseDiffCallback = object : DiffUtil.ItemCallback<NewCourse>() {
            override fun areItemsTheSame(oldItem: NewCourse, newItem: NewCourse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewCourse, newItem: NewCourse): Boolean {
                return oldItem == newItem
            }
        }
    }
}
