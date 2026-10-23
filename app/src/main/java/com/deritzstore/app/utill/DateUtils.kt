
package com.deritzstore.app.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd"

    fun getCurrentDate(): String {
        return formatDate(Date())
    }

    fun calculateDueDate(deliveryDate: String): String {
        val date = parseDate(deliveryDate)
            ?: throw IllegalArgumentException(
                "Invalid delivery date"
            )

        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, 1)

        return formatDate(calendar.time)
    }

    fun isOverdue(dueDate: String): Boolean {
        val due = parseDate(dueDate)
            ?: return false

        val today = parseDate(getCurrentDate())
            ?: return false

        return due.before(today)
    }

    fun formatDate(date: Date): String {
        return SimpleDateFormat(
            DATE_FORMAT,
            Locale.US
        ).format(date)
    }

    fun parseDate(date: String): Date? {
        val formatter = SimpleDateFormat(
            DATE_FORMAT,
            Locale.US
        )

        formatter.isLenient = false

        return try {
            formatter.parse(date)
        } catch (exception: java.text.ParseException) {
            null
        }
    }
}
