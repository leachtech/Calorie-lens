# Gemini API Integration Guide

This guide explains how to set up and use the Google Gemini API in Calorie Lens.

## Overview

Calorie Lens uses Google's Gemini AI to analyze meal images and voice input for automatic calorie and nutrition tracking.

## Getting Your API Key

### Step 1: Access Google AI Studio

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account

### Step 2: Create API Key

1. Click "Create API Key" button
2. Select or create a Google Cloud project
3. Copy the generated API key
4. **Important:** Keep this key secure and never commit it to version control

### Step 3: Configure in App

1. Open `app/src/main/java/com/calorielens/app/di/AppModule.kt`
2. Find the line:
   ```kotlin
   private const val API_KEY = "YOUR_GEMINI_API_KEY"
   ```
3. Replace `YOUR_GEMINI_API_KEY` with your actual key:
   ```kotlin
   private const val API_KEY = "AIzaSy..." // Your actual key
   ```

## API Usage

### Meal Image Analysis

The app sends base64-encoded images to Gemini for analysis:

```kotlin
suspend fun analyzeMealImage(imageBase64: String): MealAnalysisResponse {
    return geminiApiService.analyzeMeal(
        apiKey = "Bearer $apiKey",
        request = MealAnalysisRequest(image = imageBase64)
    )
}
```

### Voice Meal Analysis

Voice input is converted to text and sent to Gemini:

```kotlin
suspend fun analyzeVoiceMeal(text: String): MealAnalysisResponse {
    return geminiApiService.analyzeVoiceMeal(
        apiKey = "Bearer $apiKey",
        request = VoiceMealRequest(text = text)
    )
}
```

## API Endpoints

**Note:** The current implementation uses placeholder endpoints. Update based on official Gemini API documentation:

- Base URL: `https://generativelanguage.googleapis.com/`
- Image Analysis: `v1beta/models/gemini-pro-vision:analyzeMeal`
- Voice Analysis: `v1beta/models/gemini-pro:analyzeVoiceMeal`

## Request Format

### Image Analysis Request

```json
{
  "image": "base64_encoded_image_string",
  "prompt": "Analyze this meal and provide: total calories, macronutrient breakdown (protein, carbs, fats in grams and percentages), and a list of ingredients with their individual calorie counts."
}
```

### Response Format

```json
{
  "totalCalories": 463,
  "macronutrients": {
    "protein": {
      "grams": 162.0,
      "percentage": 35.0
    },
    "carbs": {
      "grams": 185.0,
      "percentage": 40.0
    },
    "fat": {
      "grams": 116.0,
      "percentage": 25.0
    }
  },
  "ingredients": [
    {
      "name": "Grilled Chicken Breast",
      "amount": "150g",
      "calories": 248
    }
  ]
}
```

## Error Handling

The app handles common API errors:

- **401 Unauthorized:** Invalid API key
- **429 Too Many Requests:** Rate limit exceeded
- **500 Internal Server Error:** Gemini API issue

## Rate Limits

Gemini API has rate limits:
- Free tier: Check current limits in Google AI Studio
- Paid tier: Higher limits available

Implement retry logic for rate limit errors.

## Security Best Practices

1. **Never commit API keys to version control**
2. **Use environment variables or secure storage for production**
3. **Implement API key rotation**
4. **Monitor API usage in Google Cloud Console**
5. **Set up billing alerts**

## Production Setup

For production, consider:

1. **Backend Proxy:** Don't call Gemini API directly from the app
2. **API Key Management:** Store keys securely on backend
3. **Rate Limiting:** Implement client-side rate limiting
4. **Caching:** Cache common meal analyses
5. **Offline Support:** Store recent analyses locally

## Testing

### Test API Key

```kotlin
// In AppModule.kt, you can add a test function:
fun testApiKey(): Boolean {
    return try {
        // Make a test API call
        true
    } catch (e: Exception) {
        false
    }
}
```

### Mock Responses

For development/testing, you can use mock responses:

```kotlin
class MockMealRepository : MealRepository {
    override suspend fun analyzeMealImage(imageBase64: String) = 
        MealAnalysisResponse(
            totalCalories = 500,
            macronutrients = Macronutrients(...),
            ingredients = listOf(...)
        )
}
```

## Troubleshooting

### API Key Not Working

1. Verify key is correctly copied (no extra spaces)
2. Check key has proper permissions
3. Verify project billing is enabled (if required)

### Network Errors

1. Check internet connection
2. Verify API endpoint URLs are correct
3. Check firewall/proxy settings

### Response Parsing Errors

1. Verify response format matches DTOs
2. Check for null values
3. Add error logging

## Resources

- [Gemini API Documentation](https://ai.google.dev/docs)
- [Google AI Studio](https://makersuite.google.com/)
- [API Key Management](https://cloud.google.com/docs/authentication/api-keys)

## Support

For API-related issues:
- Check Gemini API status
- Review Google Cloud Console logs
- Contact Google Cloud support

