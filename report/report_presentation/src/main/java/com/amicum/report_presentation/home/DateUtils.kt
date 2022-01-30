package com.amicum.report_presentation.home

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

fun String.toLocalDate(
    pattern: String = "yyyy-MM-dd",
    locale: Locale = Locale.getDefault()
): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(pattern, locale)
    return LocalDate.parse(this, formatter)
}

fun LocalDate.asString(
    pattern: String = "yyyy-MM-dd",
    locale: Locale = Locale.getDefault()
): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, locale)
    return formatter.format(this)
}

fun String.getLocalizedDate(): String {
    val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    val date = this.toLocalDate()
    return date.format(dateFormatter)
}