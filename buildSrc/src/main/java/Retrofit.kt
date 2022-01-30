object Retrofit {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val gson = "com.squareup.retrofit2:converter-gson:$version"
    const val rxjava3 = "com.squareup.retrofit2:adapter-rxjava3:$version"

    private const val okHttpVersion = "3.12.1"
    const val okhttp = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"

    private const val retrofitCoroutineVersion = "0.9.2"
    const val RetrofitCoroutine =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$retrofitCoroutineVersion"
}