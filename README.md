# Calorie Lens

A modern health monitoring and nutrition tracking Android application built with Jetpack Compose, Material Design 3, and Gemini AI integration.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)

## Features

- 🏥 **Health Monitoring Dashboard** - Track calories, macros, and nutrition goals
- 📸 **Food Scanner** - Camera-based meal analysis using Gemini AI
- 🎤 **Voice Logging** - Voice input for quick meal tracking
- 📊 **Meal Analysis** - Detailed breakdown of calories and macronutrients
- 🎯 **Goal Selection** - Personalized nutrition goals (Lose Weight, Gain Muscle, Maintain Weight, Improve Energy)
- 🔒 **Privacy-First** - Anonymous account creation, no email required
- 🌙 **Dark Mode** - Full dark/light theme support
- ♿ **Accessibility** - AAA compliance with TalkBack support

## Screenshots

_Add screenshots here_

## Tech Stack

- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM with StateFlow
- **Database:** Room
- **Networking:** Retrofit + OkHttp
- **AI Integration:** Google Gemini API
- **Camera:** CameraX
- **Permissions:** Accompanist Permissions
- **State Management:** ViewModel + StateFlow

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK 34
- Minimum SDK: 26 (Android 8.0)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/calorie-lens.git
   cd calorie-lens
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - File → Open → Select project directory
   - Wait for Gradle sync

3. **Configure API Key (Optional):**
   - Copy `app/src/main/java/com/calorielens/app/di/AppModule.kt.example` to `AppModule.kt`
   - Replace `YOUR_GEMINI_API_KEY_HERE` with your actual API key
   - Get your API key from [Google AI Studio](https://makersuite.google.com/app/apikey)

4. **Run the app:**
   - Click Run button (▶️) or press `Shift+F10`
   - Select device/emulator

## Building

### Debug APK
```bash
./gradlew assembleDebug
```

### Release APK
```bash
./gradlew assembleRelease
```

See [BUILD_AND_DEPLOY.md](BUILD_AND_DEPLOY.md) for detailed build instructions.

## Project Structure

```
app/
├── src/main/
│   ├── java/com/calorielens/app/
│   │   ├── data/              # Data layer (Room, API, Repositories)
│   │   ├── di/                # Dependency injection
│   │   ├── ui/                # UI layer (Compose screens, ViewModels)
│   │   └── MainActivity.kt
│   └── res/                   # Resources
├── build.gradle.kts
└── proguard-rules.pro
```

## Documentation

- [SETUP_GUIDE.md](SETUP_GUIDE.md) - Complete setup instructions
- [BUILD_AND_DEPLOY.md](BUILD_AND_DEPLOY.md) - Build and deployment guide
- [GEMINI_API_SETUP.md](GEMINI_API_SETUP.md) - Gemini API integration guide
- [DEPLOYMENT_CHECKLIST.md](DEPLOYMENT_CHECKLIST.md) - Pre-launch checklist
- [GITHUB_SETUP.md](GITHUB_SETUP.md) - GitHub repository setup

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Google Material Design 3
- Jetpack Compose team
- Google Gemini AI

## Contact

For questions or support, please open an issue on GitHub.

---

**Made with ❤️ using Jetpack Compose**
