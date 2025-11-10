@echo off
REM Script to upload Calorie Lens to GitHub

echo ========================================
echo Calorie Lens - GitHub Upload Script
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Git is not installed or not in PATH
    echo Please install Git from https://git-scm.com/
    pause
    exit /b 1
)

echo [1/5] Initializing Git repository...
git init
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to initialize git repository
    pause
    exit /b 1
)

echo.
echo [2/5] Adding all files to staging...
git add .
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to add files
    pause
    exit /b 1
)

echo.
echo [3/5] Creating initial commit...
git commit -m "Initial commit: Calorie Lens Android app

- Complete Android app with Jetpack Compose
- Material Design 3 theme system
- Room database integration
- Gemini AI API integration
- Camera and voice logging features
- Complete navigation and UI screens"
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to create commit
    pause
    exit /b 1
)

echo.
echo [4/5] Setting up main branch...
git branch -M main

echo.
echo [5/5] Ready to push to GitHub!
echo.
echo ========================================
echo NEXT STEPS:
echo ========================================
echo.
echo 1. Create a repository on GitHub:
echo    - Go to https://github.com/new
echo    - Name it (e.g., "calorie-lens")
echo    - DO NOT initialize with README
echo    - Click "Create repository"
echo.
echo 2. Copy the repository URL from GitHub
echo.
echo 3. Run this command (replace with your URL):
echo    git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
echo.
echo 4. Push to GitHub:
echo    git push -u origin main
echo.
echo ========================================
echo.
echo Alternatively, if you already have a repository URL,
echo you can run these commands now:
echo.
echo   git remote add origin YOUR_REPO_URL_HERE
echo   git push -u origin main
echo.
pause

