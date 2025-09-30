# Weather Forecast App

A modern Android weather application that displays a 3-day forecast for any city with full offline support. Built with Kotlin, Jetpack Compose, Room, and Retrofit.

## Features

- **3-Day Weather Forecast**: View temperature, weather conditions, and icons for the next 3 days
- **Offline Support**: Last fetched data is cached locally using Room Database
- **Real-time API Integration**: Powered by OpenWeatherMap API
- **Modern UI**: Beautiful, responsive interface built with Jetpack Compose & Material 3
- **Error Handling**: Graceful error messages for invalid cities or network issues
- **Loading States**: Smooth user experience with proper loading indicators

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose, Material 3
- **Architecture**: MVVM, Clean Architecture
- **Networking**: Retrofit, OkHttp
- **Local Storage**: Room Database
- **Dependency Injection**: Hilt
- **State Management**: StateFlow, MutableState
- **Image Loading**: Coil
- **Build System**: Gradle Kotlin DSL

### Demo Video
- [Demo Video Link](https://drive.google.com/file/d/1KLVK9BSogL3MDyMxBeUqEKU2D_3WB3v4/view?usp=sharing)

## Getting Started

### Prerequisites
- Android Studio Flamingo (2022.2.1) or higher
- Android SDK API 34
- [OpenWeatherMap API Key](https://home.openweathermap.org/api_keys) (free account)
