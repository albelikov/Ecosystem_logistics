plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-crypto")

    // Encryption
    implementation("org.jasypt:jasypt:1.9.3")

    // Data masking
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    // GDPR compliance
    implementation("io.micrometer:micrometer-registry-prometheus")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

application {
    mainClass.set("com.logi.dataprotection.LogiDataProtectionApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-scm-data-protection.jar")
}

tasks.named("compileKotlin") {
    kotlinOptions {
        jvmTarget = "25"
        freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.ExperimentalStdlibApi"
        )
    }
}

springBoot {
    buildInfo()
}
