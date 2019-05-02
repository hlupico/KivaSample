package co.hannalupi.kivasample.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val API_BASE_PATH = "https://api.kivaws.org/v1/"
    var client: KivaClient

    init {
       client = buildClient()
    }

    private fun buildClient() : KivaClient {
        val okHttpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder().baseUrl(API_BASE_PATH).addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.client(okHttpClient.build()).build()
        return retrofit.create(KivaClient::class.java)
    }
}