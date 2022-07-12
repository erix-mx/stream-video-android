package io.getstream.video.android.init

import io.getstream.video.android.api.CallCoordinatorService
import io.getstream.video.android.client.CallCoordinatorClient
import io.getstream.video.android.client.CallCoordinatorClientImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.wire.WireConverterFactory
import retrofit2.create

public object CallClientInitializer {

    private var callCoordinatorClient: CallCoordinatorClient? = null

    // TODO: Add a mechanism that re-builds the token in case it changes (log in/out ability).
    /**
     * Provides the [CallCoordinatorClient] for a given [userToken]. The token has to be valid.
     *
     * @param userToken The token of the current user. Throws if it's empty.
     * @return A cached instance of the [CallCoordinatorClient] used to communicate with the API.
     */
    public fun buildClient(userToken: String): CallCoordinatorClient {
        if (userToken.isBlank()) throw IllegalArgumentException("Cannot initialize io.getstream.video.android.client with empty user token.")

        val currentClient = callCoordinatorClient
        if (currentClient != null) return currentClient

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(buildInterceptor(userToken))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofitClient = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(WireConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val callService = retrofitClient.create<CallCoordinatorService>()
        val callCoordinatorClient = CallCoordinatorClientImpl(callService)

        this.callCoordinatorClient = callCoordinatorClient

        return callCoordinatorClient
    }

    private fun buildInterceptor(userToken: String): Interceptor = Interceptor {
        val original = it.request()
        val updated = original.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, userToken)
            .build()

        it.proceed(updated)
    }

    private const val HEADER_AUTHORIZATION = "authorization"
    private const val BASE_URL = "http://10.0.2.2:26991"
}