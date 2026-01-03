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
    
    // ROS (Robot Operating System) Integration - временно отключено
    // implementation(libs.rosjava.core)
    
    // IoT & Device Management
    implementation(libs.paho.mqtt.client)
    
    // Kafka for robot command/control
    implementation(libs.spring.kafka)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

