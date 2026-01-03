plugins {
    id("logistics.service")
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Spring Boot
    implementation(libs.bundles.spring.web)
    implementation(libs.spring.boot.starter.data.jpa)
    
    // Database
    implementation(libs.postgresql)
    
    // Security & Cyber Resilience
    implementation(libs.bundles.spring.security.bundle)
    
    // Monitoring & Incident Management
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.micrometer.core)
    implementation(libs.micrometer.registry.prometheus)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

