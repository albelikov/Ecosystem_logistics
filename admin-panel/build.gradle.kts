plugins {
    id("logistics.service")
    alias(libs.plugins.kotlin.spring)
}

dependencies {
        // Spring Boot
        implementation(libs.bundles.spring.web)
        implementation(libs.spring.boot.starter.data.jpa)
        implementation(libs.spring.boot.starter.thymeleaf)
        implementation(libs.spring.boot.starter.webflux) // WebClient for service integration

        // Database
        implementation(libs.postgresql)
        implementation("com.h2database:h2")

        // Security & IAM Integration
        implementation(libs.bundles.spring.security.bundle)
        implementation(libs.bundles.jwt.bundle)

        // Admin & Management Features
        implementation(libs.spring.boot.starter.actuator)
        implementation(libs.micrometer.core)
        implementation(libs.micrometer.registry.prometheus)

        // WebSocket for real-time updates
        implementation(libs.spring.boot.starter.websocket)

        // Integration with other services - отключено для тестового запуска
        // implementation(libs.spring.kafka)

        // Testing
        testImplementation(libs.spring.boot.starter.test)
        testImplementation(libs.testcontainers.postgresql)
}

