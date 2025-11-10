# Calorie Lens - Deployment Checklist

Use this checklist to ensure your app is ready for deployment.

## Pre-Build Phase

### Code Quality
- [ ] All code reviewed and approved
- [ ] No TODO comments left in production code
- [ ] All debug logs removed or properly guarded
- [ ] Code follows project style guidelines
- [ ] No hardcoded credentials or API keys
- [ ] All sensitive data properly secured

### Dependencies
- [ ] All dependencies are up to date
- [ ] No deprecated libraries in use
- [ ] All dependencies have compatible versions
- [ ] ProGuard rules tested for all dependencies

### Configuration
- [ ] API keys stored securely (not in code)
- [ ] Build configuration correct
- [ ] Version code incremented
- [ ] Version name updated
- [ ] Application ID correct

## Build Phase

### Debug Build
- [ ] `gradlew assembleDebug` succeeds
- [ ] No compilation errors
- [ ] No warnings (or acceptable warnings documented)
- [ ] APK size reasonable (< 50MB recommended)

### Release Build
- [ ] Keystore created and secured
- [ ] `keystore.properties` configured (not in git)
- [ ] `gradlew assembleRelease` succeeds
- [ ] ProGuard rules tested
- [ ] App Bundle created (`gradlew bundleRelease`)
- [ ] Release APK/AAB signed correctly

## Testing Phase

### Unit Tests
- [ ] All unit tests pass
- [ ] Test coverage acceptable (> 70% recommended)
- [ ] Edge cases tested

### Integration Tests
- [ ] API integration tests pass
- [ ] Database operations tested
- [ ] Navigation flows tested

### UI Tests
- [ ] Critical user flows tested
- [ ] Screen transitions tested
- [ ] Error states tested

### Manual Testing
- [ ] App launches without crashes
- [ ] Onboarding flow works
- [ ] All screens accessible
- [ ] Navigation works correctly
- [ ] Camera functionality works
- [ ] Voice logging works
- [ ] Permissions requested properly
- [ ] Dark mode works
- [ ] Different screen sizes tested
- [ ] Rotation handling tested

### Device Testing
- [ ] Tested on Android 8.0 (API 26)
- [ ] Tested on Android 14 (API 34)
- [ ] Tested on different manufacturers
- [ ] Tested on tablets (if supported)
- [ ] Performance acceptable on low-end devices

### Accessibility
- [ ] TalkBack tested
- [ ] All interactive elements have content descriptions
- [ ] Touch targets are at least 48dp
- [ ] Color contrast meets WCAG AA standards
- [ ] Text scales properly

## Security Phase

### Data Security
- [ ] No sensitive data in logs
- [ ] API keys not exposed
- [ ] User data encrypted at rest
- [ ] Network traffic encrypted (HTTPS)
- [ ] Proper authentication/authorization

### Permissions
- [ ] Only necessary permissions requested
- [ ] Permission rationale provided
- [ ] Graceful handling when permissions denied

### Code Security
- [ ] No SQL injection vulnerabilities
- [ ] Input validation implemented
- [ ] Error messages don't leak sensitive info

## Store Listing Phase

### Required Assets
- [ ] App icon (512x512px PNG)
- [ ] Feature graphic (1024x500px PNG)
- [ ] Phone screenshots (at least 2, 1080x1920px+)
- [ ] Tablet screenshots (if applicable)
- [ ] Short description (80 chars max)
- [ ] Full description (4000 chars max)
- [ ] Privacy policy URL

### Content
- [ ] Description is clear and accurate
- [ ] Screenshots show actual app features
- [ ] No placeholder text
- [ ] Appropriate content rating
- [ ] Keywords optimized (if applicable)

### Legal
- [ ] Privacy policy published
- [ ] Terms of service (if applicable)
- [ ] Data collection disclosed
- [ ] GDPR compliance (if applicable)
- [ ] COPPA compliance (if applicable)

## Pre-Submission Phase

### Final Checks
- [ ] App tested on fresh install
- [ ] No crashes in production build
- [ ] Analytics configured (if using)
- [ ] Crash reporting configured (if using)
- [ ] Update notes prepared
- [ ] Support email configured

### Documentation
- [ ] README updated
- [ ] Changelog updated
- [ ] API documentation updated
- [ ] User guide prepared (if needed)

## Submission Phase

### Google Play Console
- [ ] Developer account active
- [ ] App created in Play Console
- [ ] Store listing complete
- [ ] Content rating completed
- [ ] Pricing and distribution set
- [ ] App Bundle uploaded
- [ ] Release notes added
- [ ] Submitted for review

### Post-Submission
- [ ] Monitor review status
- [ ] Respond to reviewer feedback (if any)
- [ ] Prepare for launch announcement

## Post-Launch Phase

### Monitoring
- [ ] Crash reports monitored
- [ ] User reviews monitored
- [ ] Analytics reviewed
- [ ] Performance metrics tracked

### Support
- [ ] Support channels ready
- [ ] FAQ prepared
- [ ] Known issues documented

### Updates
- [ ] Update strategy planned
- [ ] Bug fix process established
- [ ] Feature roadmap prepared

## Emergency Procedures

### Rollback Plan
- [ ] Know how to rollback version
- [ ] Have previous stable version ready
- [ ] Communication plan for users

### Critical Issues
- [ ] Process for handling critical bugs
- [ ] Hotfix deployment process
- [ ] User notification plan

---

## Quick Command Reference

```bash
# Build and test
./gradlew clean lint test assembleDebug

# Release build
./gradlew bundleRelease

# Install on device
./gradlew installDebug

# Check dependencies
./gradlew app:dependencies
```

---

**Checklist Complete?** You're ready to deploy! 🚀

