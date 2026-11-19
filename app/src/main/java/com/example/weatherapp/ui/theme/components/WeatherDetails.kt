package com.example.weatherapp.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.Current

@Composable
fun WeatherDetailsSection(
    current: Current?,
    modifier: Modifier = Modifier
) {
    if (current == null) return

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Weather Details",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherMetricCard(
                title = "Feels Like",
                value = "${current.feelsLikeC.toInt()}°C",
                icon = Icons.Default.Info,
                iconTint = Color(0xFFFF7043),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            WeatherMetricCard(
                title = "Humidity",
                value = "${current.humidity}%",
                icon = Icons.Default.LocationOn,
                iconTint = Color(0xFF42A5F5),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherMetricCard(
                title = "Wind Speed",
                value = "${current.windKph} km/h",
                icon = Icons.Default.Notifications,
                iconTint = Color(0xFF26A69A),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            WeatherMetricCard(
                title = "UV Index",
                value = "${current.uv}",
                icon = Icons.Default.Warning,
                iconTint = Color(0xFFFFA726),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
