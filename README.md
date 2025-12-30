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

## Setup Instructions

### Prerequisites

Before you begin, ensure you have the following installed:

| Tool               | Version          | Purpose                                     |
|--------------------|------------------|---------------------------------------------|
| **JDK**            | 17 or higher     | Java Development Kit for Kotlin compilation |
| **Android Studio** | Ladybug or later | Android development IDE                     |
| **Xcode**          | 15.0+            | iOS development (Mac only)                  |
| **CocoaPods**      | Latest version   | iOS dependency management                   |

**Installation Links:**

- [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- [Download Android Studio](https://developer.android.com/studio)
- [Download Xcode](https://developer.apple.com/xcode/) (Mac App Store)
- [Install CocoaPods](https://cocoapods.org/)

---

### Getting TMDB API Key

You'll need a free TMDB API key to fetch movie data:

**Step 1:** Visit [TMDB](https://www.themoviedb.org/signup)

**Step 2:** Create a free account

- Fill in your details and verify your email

**Step 3:** Navigate to API settings

- Go to your profile → **Settings** → **API**
- Click **Create` or **Request an API Key**
- Choose **Developer** option
- Accept the terms of use

**Step 4:** Copy your API key

- Once generated, copy the **API Key (v3 auth)**

> 💡 **Note:** Keep your API key secure and never commit it to version control

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

