package com.example.shemajamebeli_5.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shemajamebeli_5.ActiveCourse
import com.example.shemajamebeli_5.ListItem
import com.example.shemajamebeli_5.NewCourse
import com.example.shemajamebeli_5.R
import com.example.shemajamebeli_5.databinding.ItemCurrentCourseBinding

class MainAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(DiffUtilCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.NewCourseItem -> TYPE_HORIZONTAL_RECYCLER_VIEW
            is ListItem.ActiveCourseItem -> TYPE_REGULAR_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_HORIZONTAL_RECYCLER_VIEW -> {
                val view = inflater.inflate(R.layout.item_new_course, parent, false)
                HorizontalViewHolder(view)
            }
            TYPE_REGULAR_ITEM -> {
                val binding = ItemCurrentCourseBinding.inflate(inflater, parent, false)
                ActiveCourseViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.coursesHorizontal)

        fun bind(newCourses: List<NewCourse>) {

            val adapter = HorizontalCoursesAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter.submitList(newCourses)

        }
    }

    class ActiveCourseViewHolder(private val binding : ItemCurrentCourseBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(course: ActiveCourse) {

            binding.tvTitle.text = course.title
            binding.tvBookingTime.text = course.bookingTime

            Glide.with(binding.root.context)
                .load(course.image)
                .into(binding.ivImage)

            binding.ivImageBackground.setColorFilter(Color.parseColor(course.mainColor))
            binding.ivStart.setColorFilter(Color.parseColor(course.mainColor))

        }
    }

    companion object {
        private const val TYPE_HORIZONTAL_RECYCLER_VIEW = 0
        private const val TYPE_REGULAR_ITEM = 1

        private val DiffUtilCallback = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return when {
                    oldItem is ListItem.NewCourseItem && newItem is ListItem.NewCourseItem -> oldItem.newCourse.id == newItem.newCourse.id
                    oldItem is ListItem.ActiveCourseItem && newItem is ListItem.ActiveCourseItem -> oldItem.activeCourse.id == newItem.activeCourse.id
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return when {
                    oldItem is ListItem.NewCourseItem && newItem is ListItem.NewCourseItem -> oldItem.newCourse == newItem.newCourse
                    oldItem is ListItem.ActiveCourseItem && newItem is ListItem.ActiveCourseItem -> oldItem.activeCourse == newItem.activeCourse
                    else -> false
                }
            }
        }
    }
}

