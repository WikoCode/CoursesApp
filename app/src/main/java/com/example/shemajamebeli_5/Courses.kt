package com.example.shemajamebeli_5

import com.squareup.moshi.Json

data class NewCourse(
    val id: String,
    @Json(name = "icon_type")
    val iconType: String,
    val duration: String,
    val title: String,
    val question: String,
    @Json(name = "main_color")
    val mainColor: String
)


data class ActiveCourse(
    val id: String,
    @Json(name = "booking_time")
    val bookingTime: String,
    val progress: String,
    val title: String,
    @Json(name = "main_color")
    val mainColor: String,
    @Json(name = "background_color_present")
    val backgroundColorPresent: String,
    @Json(name = "play_button_color_present")
    val playButtonColorPresent: String,
    val image: String
)


data class ApiResponse(
    @Json(name = "new_courses")
    val newCourses: List<NewCourse>,
    @Json(name = "active_courses")
    val activeCourses: List<ActiveCourse>
)

