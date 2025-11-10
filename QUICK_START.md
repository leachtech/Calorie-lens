# Quick Start - Build, Test & Deploy

## 🚀 Fastest Way to Build

### Option 1: Using Android Studio (Recommended)

1. **Open Project:**
   - Launch Android Studio
   - File → Open → Select project folder
   - Wait for Gradle sync

2. **Build:**
   - Build → Make Project (Ctrl+F9)
   - Or Build → Build Bundle(s) / APK(s) → Build APK(s)

3. **Run:**
   - Click Run button (▶️) or press Shift+F10
   - Select device/emulator
   - App installs and launches automatically

### Option 2: Using Command Line

#### Windows:
```cmd
gradlew.bat assembleDebug
```

#### Linux/Mac:
```bash
./gradlew assembleDebug
```

**Output:** `app/build/outputs/apk/debug/app-debug.apk`

## 📱 Install on Device

### Method 1: Via Android Studio
- Connect device via USB
- Enable USB debugging
- Click Run button
- App installs automatically

### Method 2: Via ADB
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Method 3: Manual Transfer
1. Build APK
2. Transfer APK to device
3. Open on device and install
4. Allow installation from unknown sources (if needed)

## ✅ Pre-Build Checklist

Before building, ensure:

- [ ] **API Key Configured:**
  - Open `app/src/main/java/com/calorielens/app/di/AppModule.kt`
  - Replace `YOUR_GEMINI_API_KEY` with your actual key

- [ ] **Gradle Synced:**
  - Android Studio: File → Sync Project with Gradle Files
  - Command line: `gradlew.bat tasks` (should work)

- [ ] **Device/Emulator Ready:**
  - Physical device: USB debugging enabled
  - Emulator: Created and running

## 🔧 Troubleshooting

### "Gradle wrapper not found"
**Solution:** The project needs Gradle wrapper. In Android Studio:
1. File → Settings → Build → Gradle
2. Select "Use Gradle from: 'wrapper' task in Gradle build script"
3. Sync project

Or create wrapper manually:
```bash
gradle wrapper --gradle-version 8.2
```

### "SDK location not found"
**Solution:** Create `local.properties` in project root:
```properties
sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\Sdk
```

### Build Fails
1. **Clean project:** Build → Clean Project
2. **Invalidate caches:** File → Invalidate Caches / Restart
3. **Check internet:** Gradle needs to download dependencies
4. **Update Gradle:** File → Settings → Build → Gradle

## 📦 Build Types

### Debug Build (Development)
```bash
gradlew.bat assembleDebug
```
- Fast build
- Includes debugging symbols
- Not optimized
- Uses debug signing

### Release Build (Production)
```bash
gradlew.bat assembleRelease
```
- Optimized
- ProGuard enabled
- Requires signing configuration
- See `BUILD_AND_DEPLOY.md` for setup

## 🧪 Testing

### Run Unit Tests
```bash
gradlew.bat test
```

### Run Instrumented Tests
```bash
gradlew.bat connectedAndroidTest
```

### Lint Check
```bash
gradlew.bat lint
```

## 📊 Build Outputs

After building, find outputs in:

- **Debug APK:** `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK:** `app/build/outputs/apk/release/app-release.apk`
- **App Bundle:** `app/build/outputs/bundle/release/app-release.aab`

## 🎯 Next Steps

1. **Test the app:**
   - Install on device
   - Test all features
   - Check for crashes

2. **Prepare for release:**
   - See `DEPLOYMENT_CHECKLIST.md`
   - Configure release signing
   - Prepare store assets

3. **Deploy:**
   - See `BUILD_AND_DEPLOY.md` for detailed steps
   - Upload to Google Play Console
   - Or distribute via other channels

## 📚 Additional Resources

- **Full Build Guide:** `BUILD_AND_DEPLOY.md`
- **Deployment Checklist:** `DEPLOYMENT_CHECKLIST.md`
- **Setup Guide:** `SETUP_GUIDE.md`
- **API Setup:** `GEMINI_API_SETUP.md`

---

**Ready to build!** Follow the steps above to get your app running. 🚀

