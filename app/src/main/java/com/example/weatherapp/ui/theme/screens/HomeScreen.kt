package com.example.weatherapp.ui.theme.screens

import android.icu.text.StringSearch
import android.os.Message
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.model.getWeatherDescription
import com.example.weatherapp.model.getWeatherIcons
import com.example.weatherapp.uistate.WeatherUiState


@Composable

fun HomeScreen(uiState: WeatherUiState,
               onSearch: (String)-> Unit
)
{

    var searchCity by remember { mutableStateOf("") }


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
                    value = searchCity,
                    onValueChange = { searchCity = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "Enter City") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),

                    keyboardActions = KeyboardActions(
                        onSearch ={onSearch(searchCity.trim())}
                    ),
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

                            if (uiState.isLoading)
                            {
                                WeatherSkeleton()
                            } else{

                            Column(
                                modifier = Modifier.weight(1.1f) // Takes up slightly more than half
                            ) {
                                // City Name with Location Arrow
                                Text(
                                    text = "${uiState.weather?.location?.name ?: "Loading..."}, India \u27A4",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.SemiBold
                                )

                                if (uiState.errorMessage != null) {

                                    WeatherError(
                                        errorMessage = uiState.errorMessage,
                                        onRetry = {
                                            onSearch(searchCity.trim())
                                        }
                                    )
                                }

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

                                if (uiState.isLoading) {
                                    CircularProgressIndicator()    // Loading indicator
                                }


                                // LARGE TEMPERATURE
                                Text(
                                    text = "${uiState.weather?.current?.tempC ?: "--"}°", // Added degree symbol
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.displayLarge,
                                    fontSize = 80.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Text(
                                    text = uiState.weather?.current?.condition?.text
                                        ?: "Loading...",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = uiState.weather?.current?.condition?.code
                                        ?.let { getWeatherDescription(it) }
                                        ?:"Checking Weather Conditions...",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium,
                                    lineHeight = 20.sp
                                )
                            }
                        }

                        // 3. RIGHT SIDE: Large Image
                        Box(
                            modifier = Modifier
                                .weight(1f) // Takes up the remaining right side
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                painter = painterResource(id = uiState.weather?.current?.condition?.code
                                    ?.let { getWeatherIcons(it) }
                                    ?:R.drawable.cloudy_weather
                                ),
                                contentDescription = "Current Weather ",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f), // Keeps it perfectly square/circular
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(22.dp))


                    Card(modifier = Modifier
                        .width(155.dp)
                        .height(56.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp))
                    {
                        Row(modifier = Modifier
                            .fillMaxSize()
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


                            VerticalDivider(modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
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

fun searchcity(city: String)
{
    Log.d("Weather Search", " Search city= $city")
}
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview()
//{
//    HomeScreen(uiState = WeatherUiState())
//}


@Composable
fun SkeletonBox(modifier: Modifier= Modifier,
                shape: RoundedCornerShape=RoundedCornerShape(8.dp)
)
{
    val shimmerBrush=rememberShimmerBrush()

    Box(modifier=modifier.background(brush = shimmerBrush,
        shape = shape
    )
    )


}

@Composable
fun WeatherSkeleton()
{
    Column()
    {


//City name placeholder
        SkeletonBox(modifier = Modifier
            .width(150.dp)
            .height(25.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

//Updated status placeholder


        SkeletonBox(modifier = Modifier
            .width(100.dp)
            .height(12.dp))
        Spacer(modifier = Modifier.height(12.dp))

// Greeting placeholder

        SkeletonBox(modifier = Modifier
            .width(130.dp)
            .height(18.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))


// Temperature Placeholder
        SkeletonBox(modifier = Modifier
            .width(150.dp)
            .height(85.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))


// Weather condition placeholder
            SkeletonBox(modifier = Modifier
            .width(120.dp)
            .height(22.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))



    }
}

@Composable
fun rememberShimmerBrush(): Brush {

    val transition = rememberInfiniteTransition(
        label = "Shimmer"
    )

    val translateAnimation by transition.animateFloat(
        initialValue = -300f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer Animation"
    )

    return Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.5f)
        ),
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(
            translateAnimation + 200f,
            translateAnimation + 200f
        )
    )
}


@Composable
fun WeatherError(errorMessage: String,
                  onRetry:()-> Unit
)
{

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {

        Icon(imageVector = Icons.Default.Warning,
            contentDescription = "Weather error"
            , modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Unable to fetch weather",
            style= MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(text = errorMessage, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry)
        {
            Text("Try Again")
        }

    }


}