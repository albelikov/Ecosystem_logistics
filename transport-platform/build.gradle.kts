plugins {
    id("logistics.service")
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Spring Boot
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
    
    // Kafka
    implementation(libs.bundles.kafka.bundle)
    
    // gRPC
    implementation(libs.bundles.grpc.bundle)
    
    // Monitoring
    implementation(libs.micrometer.core)
    implementation(libs.micrometer.registry.prometheus)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.bundles.test.bundle)
}

