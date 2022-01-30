apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation" (project(":models"))
    "implementation" (project(":core-ui"))

    "implementation" (project(":report:report_domain"))

    "implementation" (project(":authentification:auth_domain"))
    "implementation" (project(":authentification:auth_presentation"))

    "implementation" (project(":share:share_domain"))
}