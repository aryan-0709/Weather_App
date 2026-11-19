# ☀️ Weather App - Jetpack Compose & Android Clean Architecture

A modern, responsive, clean-architecture Android Weather Application built using **Kotlin**, **Jetpack Compose**, **Material 3**, **MVVM**, **Retrofit**, **Coroutines**, and **StateFlow**.

The app integrates with the **WeatherAPI** REST API to provide real-time weather information, a 3-day daily forecast, detailed weather metrics (Feels Like, Humidity, Wind Speed, UV Index), interactive city search, robust error handling, and a shimmer loading skeleton.

---

## ✨ Features

- 🌤️ **Current Weather Overview**: Displays current temperature (°C), location details (City, Country), weather condition text, detailed description, and dynamic weather condition illustrations.
- 📅 **3-Day Weather Forecast**: Displays high and low temperatures, weather condition icons, and formatted day labels (Today, Tomorrow, Day Name) for the upcoming 3 days.
- 📊 **Detailed Weather Metrics**:
  - 🌡️ **Feels Like**: Apparent temperature in Celsius.
  - 💧 **Humidity**: Relative atmospheric humidity percentage.
  - 💨 **Wind Speed**: Wind speed in kilometers per hour (km/h).
  - ☀️ **UV Index**: Ultra-violet radiation risk rating.
- 🔍 **Interactive Search**: Search for any city worldwide with instant API updates and error resilient validation.
- ⏳ **Shimmer Loading Skeleton**: Smooth placeholder animation during network requests using Jetpack Compose animation primitives.
- ⚠️ **Error Resilience**: Graceful error handling for missing cities (400), invalid API keys (401), network disconnections, and retry functionality.
- 🔒 **Secure API Key Management**: API key is isolated in `local.properties` (git-ignored) and dynamically injected into Gradle `BuildConfig`.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 2.0
- **UI Toolkit**: Jetpack Compose with Material 3 (M3)
- **Architecture**: MVVM (Model-View-ViewModel) + Repository Pattern
- **Asynchronous / Reactive**: Kotlin Coroutines, `Flow`, `StateFlow`
- **Network Layer**: Retrofit 2 + Gson Converter
- **Image & Resource Handling**: Vector Drawables & WebP condition illustrations
- **Security**: Gradle `BuildConfig` & `local.properties` secret management

### Architecture Overview

```
┌────────────────────────────────────────────────────────┐
│                   Jetpack Compose UI                   │
│         (HomeScreen, DailyForecast, WeatherDetails)     │
└───────────────────────────▲────────────────────────────┘
                            │ Collects StateFlow
┌───────────────────────────┴────────────────────────────┐
│                    WeatherViewModel                    │
│             (Manages WeatherUiState & Errors)          │
└───────────────────────────▲────────────────────────────┘
                            │ Suspends / Calls
┌───────────────────────────┴────────────────────────────┐
│                   WeatherRepository                    │
└───────────────────────────▲────────────────────────────┘
                            │ Calls Retrofit
┌───────────────────────────┴────────────────────────────┐
│                    WeatherApi (Retrofit)               │
│               (v1/forecast.json Endpoint)              │
└───────────────────────────▲────────────────────────────┘
                            │ HTTP GET Request
┌───────────────────────────┴────────────────────────────┐
│                  WeatherAPI REST Service               │
└────────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```text
com.example.weatherapp/
├── MainActivity.kt                  # Activity entry point with Edge-to-Edge & ViewModel setup
├── model/                           # Data models & Gson JSON deserialization
│   ├── Location.kt                  # Location metadata (Name, Country, Timezone)
│   ├── Current.kt                   # Current weather metrics (Temp, Wind, Humidity, UV)
│   ├── Condition.kt                 # Weather condition code, text, icon mapping
│   ├── Forecast.kt                  # 3-Day forecast models & day name formatting utility
│   └── WeatherResponse.kt           # Top-level API response container
├── remote/                          # Network Layer
│   ├── WeatherApi.kt                # Retrofit interface (@GET v1/forecast.json)
│   └── Retrofit_Instance.kt         # Retrofit singleton builder
├── repository/                      # Data Layer
│   └── WeatherRepository.kt         # Abstracted data access repository
├── uistate/                         # UI State Definition
│   └── Weather_Ui_State.kt          # Sealed/Data class representing Loading, Success, Error
├── viewmodel/                       # ViewModel & Factory
│   ├── Weather_View_Model.kt        # Coroutine-backed StateFlow ViewModel
│   └── WeatherViewModelFactory.kt   # Factory for ViewModel instantiation
└── ui/
    └── theme/                       # Compose Theming & Screens
        ├── Color.kt, Theme.kt, Type.kt
        ├── components/              # Reusable Composables
        │   ├── DailyForecast.kt     # 3-Day forecast list component
        │   ├── WeatherDetails.kt    # 2x2 Weather metric grid
        │   └── Weather_Metric_Card.kt # Individual metric card component
        └── screens/
            └── HomeScreen.kt        # Main screen containing Search, Current Temp, Forecast & Details
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Android Studio Ladybug (2024.2) or higher recommended
- **JDK**: Java 17
- **API Key**: Free API Key from [WeatherAPI](https://www.weatherapi.com/)

### Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/Weather_App.git
   cd Weather_App
   ```

2. **Configure API Key**:
   Create or edit the `local.properties` file in the root directory of your project and add your WeatherAPI key:
   ```properties
   WEATHER_API_KEY=your_weather_api_key_here
   ```

3. **Build the Application**:
   Open the project in Android Studio and run a Gradle Sync, or execute via terminal:
   ```bash
   ./gradlew assembleDebug
   ```

4. **Run Unit Tests**:
   ```bash
   ./gradlew testDebugUnitTest
   ```

---

## 🛡️ License

This project is open source and available under the [MIT License](LICENSE).
