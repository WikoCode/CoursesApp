package com.example.shemajamebeli_5

sealed class ListItem {
    data class NewCourseItem(val newCourse: NewCourse) : ListItem()
    data class ActiveCourseItem(val activeCourse: ActiveCourse) : ListItem()
}
