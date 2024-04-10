# NewsAppJetpackCompose
NewsAppJetpackCompose is a sample news application built using Jetpack Compose. It demonstrates the implementation of various UI components and architectural patterns in Jetpack Compose.

## Features

- **Modern UI:** The app showcases a modern and intuitive user interface built entirely with Jetpack Compose, providing a seamless user experience.
- **News API Integration:** It integrates with a news API to fetch the latest news articles and display them in the app.
- **WebView Implementation:** Users can view the source article from within the app itself.
- **Search Functionality:** The app allows users to search for specific news articles based on keywords.
- **Save Articles:** Users can save their favorite articles for later reading and access them in the Saved Articles section.
- **Pagination:** Articles are loaded ten at a time rather than all at once.
- **MVVM Architecture:** The app is made following the principles of MVVM architecture.

## Screenshots


<p align="center">
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/home.jpeg" width="230" height="420" align="left">
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/search.jpeg" width="230" height="420" align="center"> 
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/shorts.jpeg" width="230" height="420" align="right">
</p>

<p align="center">
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/login.jpeg" width="230" height="420" align="left">
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/signup.jpeg" width="230" height="420" align="center"> 
  <img src="https://github.com/devgala/NewsAppJetpackCompose/blob/main/saved.jpeg" width="230" height="420" align="right">
</p>


## Tech Stack

The project leverages the following libraries and technologies:

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern UI toolkit for building native Android apps.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Asynchronous programming using Kotlin.
- [Retrofit](https://square.github.io/retrofit/) - HTTP client for making API requests.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Persistence library for local data storage.
- [Glide](https://github.com/bumptech/glide) - Image loading library for displaying news article images.
- [Hilt](https://dagger.dev/hilt/gradle-setup) - Dependency injection framework.
- [Navigation](https://developer.android.com/jetpack/compose/navigation) - Navigation Library to navigate between multiple screens within the same activity.


## Getting Started

To get started with the project, follow these steps:

1. Clone the repository:

   ```
   git clone https://github.com/devgala/NewsAppJetpackCompose.git
   ```

2. Open the project in Android Studio.
   
3. Add your API key for [NewsAPI](https://newsapi.org/) and [WeatherAPI](https://www.weatherapi.com/) in the util/Constants.kt file.  

4. Build and run the app on an emulator or physical device.

## Contributors
-[Priyank Sethiya](https://github.com/Priyank-Shethia3)
-[Param Gupta](https://github.com/Param-GG)
-[Dev Gala](https://github.com/devgala)

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [News API](https://newsapi.org/) - The news data is fetched from this API.
- [Jetpack Compose Samples](https://github.com/android/compose-samples) - Reference samples provided by the Android team for Jetpack Compose.

Feel free to explore, modify, and use this project as a learning resource or as a starting point for your own news app built with Jetpack Compose.
