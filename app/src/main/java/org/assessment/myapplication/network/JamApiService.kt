package org.assessment.myapplication.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.assessment.myapplication.model.Jam
import org.assessment.myapplication.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://jamtangan-api-test.000webhostapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JamApiService {
    @GET("api/jam.php")
    suspend fun getJam(
        @Header("Authorization") userId: String
    ): List<Jam>

    @Multipart
    @POST("api/jam.php")
    suspend fun postJam(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("pemilik") pemilik: RequestBody,
        @Part image: MultipartBody.Part,
    ): OpStatus

    @FormUrlEncoded
    @POST("api/deleteJam.php")
    suspend fun deleteJam(
        @Header("Authorization") userId: String,
        @Query("id") jamId: String
    ): OpStatus
}

object JamApi {
    val service: JamApiService by lazy {
        retrofit.create(JamApiService::class.java)
    }
    fun getJamUrl(imageId: String): String {
        return "${BASE_URL}api/image.php?id=$imageId"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}