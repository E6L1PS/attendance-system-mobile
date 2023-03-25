package com.mirea.attsystem.data.dto

import java.sql.Date
import java.time.LocalDate

data class DateDTO(
    val date: String,
    val dateTimeList: List<String>
)