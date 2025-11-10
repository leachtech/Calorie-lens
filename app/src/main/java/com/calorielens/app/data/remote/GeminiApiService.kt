package com.calorielens.app.data.remote

import com.calorielens.app.data.remote.dto.MealAnalysisRequest
import com.calorielens.app.data.remote.dto.MealAnalysisResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

interface GeminiApiService {
    @POST("v1beta/models/gemini-pro-vision:analyzeMeal")
    suspend fun analyzeMeal(
        @Header("Authorization") apiKey: String,
        @Body request: MealAnalysisRequest
    ): MealAnalysisResponse

    @POST("v1beta/models/gemini-pro:analyzeVoiceMeal")
    suspend fun analyzeVoiceMeal(
        @Header("Authorization") apiKey: String,
        @Body request: VoiceMealRequest
    ): MealAnalysisResponse
}

data class VoiceMealRequest(
    val text: String
)
