plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Project modules
    implementation(project(":scm-iam"))
    implementation(project(":scm-audit"))
    implementation(project(":scm-data-protection"))
    implementation(project(":fms-service"))
    implementation(project(":tms-service"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // AI/ML for autonomous decisions
    implementation("org.springframework.boot:spring-boot-starter-ai")

    // Autonomous vehicle protocols
    implementation("org.eclipse.mqtt:org.eclipse.paho.client.mqttv3:1.2.5")

    // Real-time decision making
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // Edge computing
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("com.h2database:h2")
}

application {
    mainClass.set("com.logi.autonomous.LogiAutonomousApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-autonomous-ops.jar")
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
