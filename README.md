# KMP-MovieApp

A Kotlin Multiplatform (KMP) application for browsing movies,  
built for Android and iOS platforms with shared business logic and native UI implementations.

## Features

📱 **Movie List**: Browse popular and trending movies\
🔍 **Search**: Search for movies by title\
📄 **Movie Details**: View detailed information about any movie\
🎨 **Cross Platform UI**: Compose UI implementations for optimal user experience

## Implementation Status

**Core Features:**

- [x] Movie list
- [ ] Pagination
- [x] Movie detail screen
- [x] Search functionality
- [ ] Local Data caching

## API Choice

The Movie Database [(TMDB) API](https://www.themoviedb.org/documentation/api)
We use TMDB API for fetching movie data.

## Design Patterns

**MVVM**: For presentation layer state management\
**Repository Pattern**: Abstracts data sources\
**Dependency Injection**: Using Koin for shared DI\
**Single Source of Truth**: Network as source of truth\
**Unidirectional Data Flow**: Clear data flow from UI to domain to data layer

## Tech Stack

**Kotlin Multiplatform**: 2.0.0+\
**Ktor**: HTTP client for API calls\
**SQLDelight**: Local database for caching\
**Kotlinx Serialization**: JSON parsing\
**Kotlinx Coroutines**: Asynchronous programming\
**Koin**: Dependency injection\
**Jetpack Compose**: Modern declarative multiplatform UI

### Configuration

Create a `local.properties` file in the project root:

```
project-root/
└── local.properties
```

Add the following configuration:

```properties
sdk.dir=/path/to/Android/sdk
movieApiKey=your_api_key_here
```

> **Note:** Replace `your_api_key_here` with your TMDB API key.

