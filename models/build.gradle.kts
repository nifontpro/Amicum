apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (Compose.uiGraphics) // Для типа ImageBitmap
}