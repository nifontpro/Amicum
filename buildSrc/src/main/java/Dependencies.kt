import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.e.amicummobile"
    const val compile_sdk = 31
    const val min_sdk = 26
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Modules {
    const val app = ":app"
    const val repository = ":repository"
    const val db = ":db"
    const val models = ":models"
    const val utils = ":utils"
    const val config = ":config"

    //Features
    const val historyScreen = ":historyScreen"
}

object Version {
    const val koin = "3.1.2"
    const val coil = "1.4.0"
    const val room = "2.4.0"
    const val cardView = "1.0.0"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val navigation = "2.3.5"
    const val core = "1.7.0"
    const val appCompat = "1.3.1"
    const val gson = "2.8.8"
    const val recyclerview = "1.2.1"
    const val lifecycleExtensions = "2.2.0"
    const val lifecycleViewModel = "2.4.0"
    const val databinding = "3.1.4"
    const val design = "31.0.0"
    const val legacy = "1.0.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.3"
    const val espressoCore = "3.5.0-alpha03"
    const val espressoIntents = "3.5.0-alpha03"
    const val mockitoCore = "4.1.0"
    const val mockitoInline = "4.1.0"
    const val mockitoKotlin = "1.5.0"
    const val robolectric = "4.6"
    const val testCore = "1.4.0"
    const val testRunner = "1.4.0"
    const val testExtTruth = "1.4.0"
    const val fragmentTesting = "1.4.0"
    const val uiautomator = "2.2.0"
}

object KoinDI {
    const val core =
        "io.insert-koin:koin-core:${Version.koin}"                                     //Основная библиотека
    const val koinAndroid =
        "io.insert-koin:koin-android:${Version.koin}"                           //Koin для поддержки Android (Scope,ViewModel ...)
    const val koinAndroidCompat =
        "io.insert-koin:koin-android-compat:${Version.koin}"              //Для совместимости с Java
}

object ImageLoader {
    const val coil = "io.coil-kt:coil:${Version.coil}"
}

object KotlinOld {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Kotlin.version}"
}

object AndroidDesign {
    const val design = "com.android.support:design:${Version.design}"
    const val cardView = "androidx.cardview:cardview:${Version.cardView}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerview}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleExtensions}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleViewModel}"

}

object AndroidBase {
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
    const val databinding = "com.android.databinding:compiler:${Version.databinding}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Version.legacy}"
}

object TestUnit {
    const val robolectric = "org.robolectric:robolectric:${Version.robolectric}"
    const val junit = "junit:junit:${Version.junit}"
    const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
    const val testCore = "androidx.test:core:${Version.testCore}"
    const val testRunner = "androidx.test:runner:${Version.testRunner}"
    const val testExtTruth = "androidx.test.ext:truth:${Version.testExtTruth}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Version.espressoIntents}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Version.fragmentTesting}"
    const val uiautomator = "androidx.test.uiautomator:uiautomator:${Version.uiautomator}"
}

object Mockito {
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockitoCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInline}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Version.mockitoKotlin}"
}