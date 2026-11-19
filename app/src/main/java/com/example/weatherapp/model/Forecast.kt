package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay> = emptyList()
)

data class ForecastDay(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Long,
    val day: Day
)

data class Day(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,

    @SerializedName("maxtemp_f")
    val maxTempF: Double,

    @SerializedName("mintemp_c")
    val minTempC: Double,

    @SerializedName("mintemp_f")
    val minTempF: Double,

    @SerializedName("avgtemp_c")
    val avgTempC: Double,

    @SerializedName("avgtemp_f")
    val avgTempF: Double,

    val condition: Condition,

    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,

    @SerializedName("avghumidity")
    val avgHumidity: Double? = null,

    @SerializedName("maxwind_kph")
    val maxWindKph: Double? = null,

    val uv: Double? = null
)

/**
 * Utility function to convert a date string (yyyy-MM-dd) into a user-friendly day label.
 * Example: "2026-03-31" -> "Today", "Tomorrow", "Thursday", etc.
 */
fun formatDayName(dateString: String): String {
    return try {
        val inputFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        val date = inputFormat.parse(dateString) ?: return dateString

        val calendar = java.util.Calendar.getInstance()
        val today = calendar.get(java.util.Calendar.DAY_OF_YEAR)
        val todayYear = calendar.get(java.util.Calendar.YEAR)

        val targetCalendar = java.util.Calendar.getInstance().apply { time = date }
        val targetDay = targetCalendar.get(java.util.Calendar.DAY_OF_YEAR)
        val targetYear = targetCalendar.get(java.util.Calendar.YEAR)

        when {
            targetYear == todayYear && targetDay == today -> "Today"
            targetYear == todayYear && targetDay == today + 1 -> "Tomorrow"
            else -> {
                val outputFormat = java.text.SimpleDateFormat("EEEE", java.util.Locale.getDefault())
                outputFormat.format(date)
            }
        }
    } catch (e: Exception) {
        dateString
    }
}
