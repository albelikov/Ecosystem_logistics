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
    
    // Kafka for reverse logistics events
    implementation(libs.spring.kafka)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

