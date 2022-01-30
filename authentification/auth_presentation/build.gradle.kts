apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation" (project(":core-ui"))
    "implementation" (project(":models"))
    "implementation" (project(":authentification:auth_domain"))
    "implementation" (project(":report:report_domain"))
    "implementation" (project(":share:share_domain"))
}