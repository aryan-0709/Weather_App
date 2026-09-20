package com.example.weatherapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun HomeScreen()
{

    var searchText by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background)
    {
//innerPadding->
paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
//            .padding(innerPadding)
            .padding(bottom = paddingValues.calculateBottomPadding())
            .imePadding()
            .background(MaterialTheme.colorScheme.background))
        //Headder
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "Enter City") },
                    leadingIcon =
                        {
                            Icon(Icons.Default.Search, contentDescription = "null")
                        },
                    trailingIcon =
                        {
                            Icon(Icons.Default.LocationOn, contentDescription = "null")
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    )

                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = {/*Later*/ })
                {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notification",
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(28.dp)
                    )


                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.padding(start = 22.dp))
            {
                item {
                        // 1. Main Row to split screen into Left (Text) and Right (Image)
                        Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.Top
                        ) {
                        // 2. LEFT COLUMN: Holds all text info
                        Column(
                            modifier = Modifier.weight(1.1f) // Takes up slightly more than half
                        ) {
                            // City Name with Location Arrow
                            Text(
                                text = "Faridabad, India \u27A4",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            // Updated Status with a small Dot
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Small Circle Dot
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color(0xFF8A84FF), shape = CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Updated just now",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(
                                text = "Good Morning! \uD83D\uDC4B",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleMedium
                            )

                            // LARGE TEMPERATURE
                            Text(
                                text = "28°", // Added degree symbol
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.displayLarge,
                                fontSize = 80.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = "Partly Cloudy",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Sun behind the clouds.\nPleasant and warm.",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp
                            )
                        }

                        // 3. RIGHT SIDE: Large Image
                        Box(
                            modifier = Modifier
                                .weight(1f) // Takes up the remaining right side
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                painter = painterResource(id = com.example.weatherapp.R.drawable.headder_image),
                                contentDescription = "Weather Illustration",
                                modifier = Modifier
                                    .fillMaxWidth()
                                  .aspectRatio(1f), // Keeps it perfectly square/circular
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(22.dp))


                    Card(modifier = Modifier.width(155.dp).height(56.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp))
                    {
                        Row(modifier = Modifier.fillMaxSize()
                            .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Maximum Temperature",
                                tint = Color.Red,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(text = "32°",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold)


                            VerticalDivider(modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                                .fillMaxHeight(0.6f),
                                thickness = 1.dp,
                                color = Color.LightGray.copy(alpha = 0.6f))

//                            Spacer(modifier = Modifier.width(12.dp))

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Minimum Temperature",
                                tint = Color(0xFF8A84FF),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(text = "24°",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold)


                        }


                    }


                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview()
{
    HomeScreen()
}
