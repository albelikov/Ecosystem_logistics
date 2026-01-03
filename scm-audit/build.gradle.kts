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
    
    // Immutable logging (CISA AIS compliance)
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)
    
    // Security
    implementation(libs.spring.boot.starter.security)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

