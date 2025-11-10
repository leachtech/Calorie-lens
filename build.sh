#!/bin/bash
# Build script for Calorie Lens Android App

echo "🔨 Building Calorie Lens..."

# Clean previous builds
echo "Cleaning previous builds..."
./gradlew clean

# Run linting
echo "Running lint checks..."
./gradlew lint

# Build debug APK
echo "Building debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✅ Debug APK built successfully!"
    echo "📦 Location: app/build/outputs/apk/debug/app-debug.apk"
    
    # Check if device is connected
    if adb devices | grep -q "device$"; then
        echo "📱 Installing on connected device..."
        ./gradlew installDebug
        echo "✅ App installed successfully!"
    else
        echo "⚠️  No device connected. APK ready for manual installation."
    fi
else
    echo "❌ Build failed. Check errors above."
    exit 1
fi

