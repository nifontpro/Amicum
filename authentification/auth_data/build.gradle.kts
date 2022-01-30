apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (project(":authentification:auth_domain"))
    "implementation" (Compose.uiGraphics)
    "implementation" (project(":models"))
}