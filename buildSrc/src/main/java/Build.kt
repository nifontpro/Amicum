object Build {

    private const val androidBuildToolsVersion = "7.1.0"

    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    const val hiltAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${DaggerHilt.version}"
}