apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (project(":share:share_domain"))

    "kapt"(Room.compiler)
    "implementation"(Room.ktx)
    "implementation"(Room.runtime)
}