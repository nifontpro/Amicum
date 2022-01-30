apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (project(Modules.models))
    "implementation" (Compose.uiGraphics) // Для типа ImageBitmap
}