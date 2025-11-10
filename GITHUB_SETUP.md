# GitHub Repository Setup Guide

## Quick Setup

### Option 1: Create New Repository on GitHub

1. **Go to GitHub:**
   - Visit [github.com](https://github.com)
   - Sign in to your account

2. **Create New Repository:**
   - Click the "+" icon → "New repository"
   - Repository name: `calorie-lens` (or your preferred name)
   - Description: "Calorie Lens - AI-powered nutrition tracking Android app"
   - Choose: Public or Private
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
   - Click "Create repository"

3. **Copy Repository URL:**
   - Copy the HTTPS or SSH URL (e.g., `https://github.com/yourusername/calorie-lens.git`)

### Option 2: Use Existing Repository

If you already have a GitHub repository, just use its URL.

## Upload Project to GitHub

### Step 1: Initialize Git (if not already done)

```bash
git init
```

### Step 2: Add All Files

```bash
git add .
```

### Step 3: Create Initial Commit

```bash
git commit -m "Initial commit: Calorie Lens Android app"
```

### Step 4: Add Remote Repository

Replace `YOUR_USERNAME` and `YOUR_REPO_NAME` with your actual values:

```bash
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
```

### Step 5: Push to GitHub

```bash
git branch -M main
git push -u origin main
```

## Complete Command Sequence

```bash
# Initialize git (if needed)
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit: Calorie Lens Android app"

# Add remote (replace with your repo URL)
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## Important: Before Pushing

### ⚠️ Security Checklist

Before pushing to GitHub, ensure you've:

- [ ] **Removed API Keys:**
  - The `.gitignore` excludes `AppModule.kt` by default
  - If you want to include it, create `AppModule.kt.example` with placeholder values
  - Never commit actual API keys

- [ ] **No Sensitive Data:**
  - No keystore files (`.jks`, `.keystore`)
  - No `keystore.properties`
  - No `local.properties` (contains SDK path)

- [ ] **Review .gitignore:**
  - Check that `.gitignore` is properly configured
  - All build artifacts are excluded

### Create AppModule.kt.example

If you want to share the structure but not the key:

1. Copy `app/src/main/java/com/calorielens/app/di/AppModule.kt`
2. Create `AppModule.kt.example`
3. Replace actual API key with placeholder:
   ```kotlin
   private const val API_KEY = "YOUR_GEMINI_API_KEY_HERE"
   ```
4. Update `.gitignore` to allow `AppModule.kt.example`

## Alternative: Use GitHub CLI

If you have GitHub CLI installed:

```bash
# Create repo and push in one command
gh repo create calorie-lens --public --source=. --remote=origin --push
```

## Repository Structure on GitHub

Your repository will contain:

```
calorie-lens/
├── app/
│   ├── src/main/
│   │   ├── java/          # All Kotlin source code
│   │   └── res/           # Resources
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
├── .gitignore
└── Documentation files
```

## After Uploading

### 1. Add Repository Description

On GitHub repository page:
- Click "⚙️ Settings" → "General"
- Add description: "AI-powered nutrition tracking Android app built with Jetpack Compose"

### 2. Add Topics/Tags

Click "Add topics" and add:
- `android`
- `kotlin`
- `jetpack-compose`
- `nutrition-tracking`
- `health-app`
- `material-design-3`

### 3. Update README

The README.md is already created. You may want to:
- Add screenshots
- Add badges (build status, etc.)
- Update with your specific information

### 4. Add License (Optional)

If you want to add a license:
- Go to repository → "Add file" → "Create new file"
- Name it `LICENSE`
- Choose a license template (MIT, Apache 2.0, etc.)

## Troubleshooting

### "Repository not found"
- Check repository URL is correct
- Verify you have access to the repository
- Check authentication (use GitHub CLI or SSH keys)

### "Permission denied"
- Set up SSH keys: [GitHub SSH Guide](https://docs.github.com/en/authentication/connecting-to-github-with-ssh)
- Or use HTTPS with Personal Access Token

### "Large files"
- If files are too large, use Git LFS:
  ```bash
  git lfs install
  git lfs track "*.apk"
  git add .gitattributes
  ```

### "API key in commit history"
If you accidentally committed an API key:
1. Use `git filter-branch` or BFG Repo-Cleaner
2. Or create a new repository
3. Rotate/regenerate the exposed API key

## Best Practices

1. **Never commit:**
   - API keys
   - Keystore files
   - `local.properties`
   - Build artifacts

2. **Always commit:**
   - Source code
   - Gradle files
   - Documentation
   - `.gitignore`

3. **Use branches:**
   ```bash
   git checkout -b develop
   # Make changes
   git commit -m "Feature: Add new screen"
   git push origin develop
   ```

4. **Write good commit messages:**
   - Use present tense: "Add feature" not "Added feature"
   - Be descriptive
   - Reference issues: "Fix #123"

## Next Steps

After uploading:

1. **Set up GitHub Actions** (optional):
   - Create `.github/workflows/android.yml` for CI/CD
   - See `BUILD_AND_DEPLOY.md` for example

2. **Add Issues Template:**
   - Create `.github/ISSUE_TEMPLATE/bug_report.md`
   - Create `.github/ISSUE_TEMPLATE/feature_request.md`

3. **Add Pull Request Template:**
   - Create `.github/pull_request_template.md`

4. **Enable GitHub Discussions:**
   - Settings → General → Features → Discussions

---

**Ready to upload!** Follow the steps above to push your code to GitHub. 🚀

