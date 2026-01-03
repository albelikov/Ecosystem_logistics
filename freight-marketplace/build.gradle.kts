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
    
    // Blockchain (Web3j для Ethereum)
    implementation(libs.web3j.core)
    
    // Security
    implementation(libs.spring.boot.starter.security)
    implementation(libs.bundles.jwt.bundle)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

