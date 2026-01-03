plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Security frameworks
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Keycloak integration
    implementation("org.keycloak:keycloak-spring-boot-starter:23.0.0")
    implementation("org.keycloak:keycloak-admin-client:23.0.0")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // Rate limiting
    implementation("com.bucket4j:bucket4j-core:8.7.0")

    // Audit
    implementation(project(":scm-audit"))

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

application {
    mainClass.set("com.logi.iam.LogiIamApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-scm-iam.jar")
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
