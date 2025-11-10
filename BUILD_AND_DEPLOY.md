# Build, Test, and Deploy Guide

## Quick Start Commands

### 1. Build Debug APK

```bash
# Windows
gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

### 2. Build Release APK

```bash
# Windows
gradlew.bat assembleRelease

# Linux/Mac
./gradlew assembleRelease
```

**Note:** Release builds require signing configuration. See "Release Build Setup" below.

### 3. Install on Connected Device

```bash
# Windows
gradlew.bat installDebug

# Linux/Mac
./gradlew installDebug
```

### 4. Run Tests

```bash
# Unit tests
gradlew.bat test

# Instrumented tests
gradlew.bat connectedAndroidTest
```

### 5. Lint Check

```bash
gradlew.bat lint
```

## Build Configuration

### Debug Build
- **Purpose:** Development and testing
- **Signing:** Uses debug keystore (auto-generated)
- **Optimization:** None
- **Size:** Larger APK

### Release Build
- **Purpose:** Production deployment
- **Signing:** Requires release keystore
- **Optimization:** ProGuard enabled
- **Size:** Smaller, optimized APK

## Release Build Setup

### Step 1: Generate Keystore

```bash
keytool -genkey -v -keystore calorie-lens-release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias calorie-lens
```

### Step 2: Create `keystore.properties`

Create `keystore.properties` in the project root (add to `.gitignore`):

```properties
storePassword=your-store-password
keyPassword=your-key-password
keyAlias=calorie-lens
storeFile=calorie-lens-release.jks
```

### Step 3: Update `app/build.gradle.kts`

Add signing configuration:

```kotlin
android {
    // ... existing code ...
    
    signingConfigs {
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            val keystoreProperties = java.util.Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(java.io.FileInputStream(keystorePropertiesFile))
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

## Testing Checklist

### Pre-Build Checks
- [ ] All dependencies synced
- [ ] No compilation errors
- [ ] Linter passes
- [ ] API key configured (if using Gemini API)

### Functional Testing
- [ ] App launches successfully
- [ ] Onboarding flow works
- [ ] Navigation between screens
- [ ] Camera permission and functionality
- [ ] Voice logging permission and functionality
- [ ] Database operations
- [ ] API calls (if configured)

### Device Testing
- [ ] Test on Android 8.0 (API 26)
- [ ] Test on Android 14 (API 34)
- [ ] Test on different screen sizes
- [ ] Test in dark mode
- [ ] Test with TalkBack enabled

## Deployment Options

### Option 1: Direct Install (Development)

1. Build debug APK:
   ```bash
   gradlew.bat assembleDebug
   ```

2. Transfer to device:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Option 2: Google Play Store (Production)

#### Prerequisites
- [ ] Google Play Developer account ($25 one-time fee)
- [ ] Release APK built and signed
- [ ] Store listing assets prepared
- [ ] Privacy policy URL
- [ ] App content rating completed

#### Steps

1. **Create App Bundle:**
   ```bash
   gradlew.bat bundleRelease
   ```
   Output: `app/build/outputs/bundle/release/app-release.aab`

2. **Upload to Play Console:**
   - Go to [Google Play Console](https://play.google.com/console)
   - Create new app
   - Upload AAB file
   - Complete store listing
   - Submit for review

#### Store Listing Requirements

- **App Icon:** 512x512px PNG
- **Feature Graphic:** 1024x500px PNG
- **Screenshots:** 
  - Phone: At least 2, max 8 (1080x1920px or higher)
  - Tablet: Optional (1200x1920px or higher)
- **Short Description:** 80 characters max
- **Full Description:** 4000 characters max
- **Privacy Policy:** Required URL

### Option 3: Internal Testing (Play Store)

1. Create internal testing track in Play Console
2. Upload AAB to internal testing
3. Add testers via email
4. Testers receive link to install

### Option 4: Firebase App Distribution

1. Set up Firebase project
2. Add App Distribution to Firebase
3. Upload APK/AAB
4. Invite testers

## Version Management

### Update Version

In `app/build.gradle.kts`:

```kotlin
defaultConfig {
    versionCode = 2  // Increment for each release
    versionName = "1.0.1"  // Semantic versioning
}
```

### Versioning Strategy

- **Major (1.0.0):** Breaking changes
- **Minor (0.1.0):** New features, backward compatible
- **Patch (0.0.1):** Bug fixes

## Build Optimization

### Enable R8 Full Mode

In `gradle.properties`:
```properties
android.enableR8.fullMode=true
```

### Reduce APK Size

1. Enable ProGuard/R8 (already enabled)
2. Remove unused resources:
   ```kotlin
   android {
       buildTypes {
           release {
               shrinkResources = true
           }
       }
   }
   ```
3. Use App Bundle instead of APK
4. Enable code shrinking

## Troubleshooting Build Issues

### Issue: "SDK location not found"
**Solution:** Create `local.properties`:
```properties
sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\Sdk
```

### Issue: "Gradle sync failed"
**Solution:**
1. Check internet connection
2. Invalidate caches: File → Invalidate Caches / Restart
3. Clean project: Build → Clean Project

### Issue: "Out of memory"
**Solution:** Increase heap size in `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=512m
```

### Issue: "Duplicate class found"
**Solution:**
1. Check for duplicate dependencies
2. Use `./gradlew app:dependencies` to see dependency tree
3. Exclude conflicting dependencies

## Continuous Integration (CI/CD)

### GitHub Actions Example

Create `.github/workflows/android.yml`:

```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
      
    - name: Build with Gradle
      run: ./gradlew assembleDebug
      
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

## Pre-Deployment Checklist

- [ ] Version code and name updated
- [ ] All tests passing
- [ ] Linter checks passed
- [ ] ProGuard rules tested
- [ ] API keys secured (not in code)
- [ ] Privacy policy published
- [ ] Store listing complete
- [ ] Screenshots prepared
- [ ] App tested on multiple devices
- [ ] Performance tested
- [ ] Security review completed
- [ ] Backup and restore tested
- [ ] Deep linking tested

## Post-Deployment

- [ ] Monitor crash reports (Firebase Crashlytics)
- [ ] Monitor analytics
- [ ] Collect user feedback
- [ ] Plan next release
- [ ] Update documentation

## Useful Commands Reference

```bash
# Clean build
gradlew.bat clean

# Build debug
gradlew.bat assembleDebug

# Build release
gradlew.bat assembleRelease

# Build bundle
gradlew.bat bundleRelease

# Install debug
gradlew.bat installDebug

# Run tests
gradlew.bat test
gradlew.bat connectedAndroidTest

# Lint
gradlew.bat lint

# Check dependencies
gradlew.bat app:dependencies

# View tasks
gradlew.bat tasks
```

---

**Ready to deploy!** Follow the steps above to build, test, and deploy your app. 🚀

