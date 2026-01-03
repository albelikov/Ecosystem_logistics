plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Project modules
    implementation(project(":scm-iam"))
    implementation(project(":scm-audit"))
    implementation(project(":scm-data-protection"))
    implementation(project(":tms-service"))
    implementation(project(":fms-service"))
    implementation(project(":freight-marketplace"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Multi-modal transport
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // Contract management
    // implementation("org.springframework.boot:spring-boot-starter-contract") // Requires spring-cloud-contract

    // Digital signatures
    implementation("org.springframework.boot:spring-boot-starter-crypto")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("com.h2database:h2")
}

application {
    mainClass.set("com.logi.transport.LogiTransportPlatformApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-transport-platform.jar")
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
