@echo off
REM Build script for Calorie Lens Android App (Windows)

echo 🔨 Building Calorie Lens...

REM Clean previous builds
echo Cleaning previous builds...
call gradlew.bat clean

REM Run linting
echo Running lint checks...
call gradlew.bat lint

REM Build debug APK
echo Building debug APK...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo ✅ Debug APK built successfully!
    echo 📦 Location: app\build\outputs\apk\debug\app-debug.apk
    
    REM Check if device is connected
    adb devices | findstr "device$" >nul
    if %ERRORLEVEL% EQU 0 (
        echo 📱 Installing on connected device...
        call gradlew.bat installDebug
        echo ✅ App installed successfully!
    ) else (
        echo ⚠️  No device connected. APK ready for manual installation.
    )
) else (
    echo ❌ Build failed. Check errors above.
    exit /b 1
)

pause

