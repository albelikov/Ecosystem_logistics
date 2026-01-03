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
    
    // IoT & Device Management
    implementation(libs.paho.mqtt.client)
    
    // GIS for fleet tracking (minimal)
    implementation(libs.bundles.gis.bundle.minimal)
    
    // Kafka for real-time telemetry
    implementation(libs.spring.kafka)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

