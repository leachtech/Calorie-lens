# Calorie Lens - Complete Setup Guide

This guide will walk you through setting up the Calorie Lens Android project from scratch.

## Prerequisites

### Required Software

1. **Android Studio**
   - Version: Hedgehog (2023.1.1) or later
   - Download from: [developer.android.com/studio](https://developer.android.com/studio)

2. **JDK (Java Development Kit)**
   - Version: JDK 17 or later
   - Included with Android Studio, or download separately

3. **Android SDK**
   - Minimum SDK: 26 (Android 8.0)
   - Target SDK: 34 (Android 14)
   - Install via Android Studio SDK Manager

### Required Accounts

1. **Google Account** - For Gemini API access
2. **Git** (optional) - For version control

## Step-by-Step Setup

### Step 1: Clone/Download Project

If using Git:
```bash
git clone <repository-url>
cd CalorieLens
```

Or download and extract the ZIP file.

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Select **"Open"** or **"Open an Existing Project"**
3. Navigate to the project directory
4. Click **"OK"**

### Step 3: Sync Gradle

1. Android Studio will automatically detect the project
2. Click **"Sync Now"** when prompted
3. Wait for Gradle sync to complete (may take several minutes on first run)

**If sync fails:**
- Go to **File → Invalidate Caches / Restart**
- Select **"Invalidate and Restart"**
- Wait for Android Studio to restart and sync again

### Step 4: Configure Gemini API Key

1. Get your API key:
   - Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Sign in with Google account
   - Click **"Create API Key"**
   - Copy the generated key

2. Add to project:
   - Open `app/src/main/java/com/calorielens/app/di/AppModule.kt`
   - Find: `private const val API_KEY = "YOUR_GEMINI_API_KEY"`
   - Replace with: `private const val API_KEY = "your-actual-key-here"`

### Step 5: Set Up Android Device/Emulator

#### Option A: Physical Device

1. Enable Developer Options:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
   - Go back to **Settings → Developer Options**
   - Enable **USB Debugging**

2. Connect device:
   - Connect via USB
   - Accept USB debugging prompt on device
   - Verify device appears in Android Studio

#### Option B: Android Emulator

1. Open **Device Manager**:
   - Click **Tools → Device Manager** in Android Studio
   - Or click the device icon in toolbar

2. Create Virtual Device:
   - Click **"Create Device"**
   - Select a device (e.g., Pixel 6)
   - Click **"Next"**

3. Download System Image:
   - Select a system image (API 34 recommended)
   - Click **"Download"** if needed
   - Wait for download to complete
   - Click **"Next"**

4. Configure AVD:
   - Review settings
   - Click **"Finish"**

### Step 6: Build and Run

1. Select device/emulator from device dropdown (top toolbar)

2. Click **Run** button (▶️) or press `Shift+F10`

3. Wait for build to complete:
   - First build may take 5-10 minutes
   - Subsequent builds are faster

4. App will install and launch on device/emulator

## Verifying Installation

### Check 1: App Launches
- App should open to Welcome screen
- No crashes or errors

### Check 2: Navigation Works
- Tap "Get Started"
- Should navigate to privacy screen
- Continue through onboarding

### Check 3: Permissions
- Camera permission requested when accessing scanner
- Microphone permission requested for voice logging

### Check 4: Database
- App should create local database
- No database errors in Logcat

## Troubleshooting

### Build Errors

**Error: "SDK location not found"**
- Solution: Set `ANDROID_HOME` environment variable
- Or set `sdk.dir` in `local.properties`

**Error: "Gradle sync failed"**
- Solution: Check internet connection
- Try: **File → Invalidate Caches / Restart**
- Check Gradle version compatibility

**Error: "Cannot resolve symbol"**
- Solution: Clean and rebuild:
  - **Build → Clean Project**
  - **Build → Rebuild Project**

### Runtime Errors

**App crashes on launch:**
- Check Logcat for error messages
- Verify all dependencies are synced
- Check if device/emulator meets minimum requirements

**Camera not working:**
- Verify camera permission is granted
- Test on physical device (emulators may have issues)
- Check if device has camera hardware

**API calls failing:**
- Verify API key is correct
- Check internet connection
- Verify API key has proper permissions
- Check API quota/limits

### Performance Issues

**Slow build times:**
- Enable Gradle daemon: Already enabled by default
- Increase heap size in `gradle.properties`:
  ```
  org.gradle.jvmargs=-Xmx4096m
  ```
- Use SSD instead of HDD

**App runs slowly:**
- Test on physical device instead of emulator
- Close other apps
- Check device has sufficient RAM (2GB+ recommended)

## Development Tips

### Enable Logging

Add logging to see what's happening:
```kotlin
import android.util.Log

Log.d("CalorieLens", "Debug message")
```

View logs in Android Studio's **Logcat** panel.

### Debug Mode

- Debug builds include logging
- Release builds are optimized and smaller
- Use debug builds for development

### Hot Reload

Jetpack Compose supports hot reload:
- Make code changes
- Press `Ctrl+F10` (Windows/Linux) or `Cmd+R` (Mac)
- Changes apply without full rebuild

## Next Steps

1. **Customize Theme:**
   - Edit `ui/theme/Color.kt` for brand colors
   - Modify `ui/theme/Theme.kt` for theme settings

2. **Add Features:**
   - Implement additional screens
   - Add more meal types
   - Enhance analytics

3. **Testing:**
   - Write unit tests
   - Add UI tests
   - Test on multiple devices

4. **Prepare for Release:**
   - Configure app signing
   - Optimize for release
   - Prepare store listing

## Getting Help

- **Documentation:** See `README.md` and `GEMINI_API_SETUP.md`
- **Android Docs:** [developer.android.com](https://developer.android.com)
- **Compose Docs:** [developer.android.com/jetpack/compose](https://developer.android.com/jetpack/compose)
- **Issues:** Check GitHub issues or create new one

## Common Commands

```bash
# Clean project
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test

# Check for updates
./gradlew dependencyUpdates
```

## Project Structure Overview

```
CalorieLens/
├── app/                    # Main app module
│   ├── src/main/
│   │   ├── java/        # Kotlin source code
│   │   └── res/         # Resources (strings, layouts, etc.)
│   └── build.gradle.kts # App-level build config
├── build.gradle.kts     # Project-level build config
├── settings.gradle.kts  # Project settings
├── gradle.properties    # Gradle properties
└── README.md           # Project documentation
```

---

**Setup complete!** You're ready to start developing Calorie Lens. 🎉

