plugins {
    id("logistics.service")
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Spring Boot
    implementation(libs.bundles.spring.web)
    implementation(libs.spring.boot.starter.data.jpa)
    
    // Security & Data Protection
    implementation(libs.bundles.spring.security.bundle)
    
    // FIPS-compliant cryptography (NIST 800-171, FedRAMP)
    implementation(libs.bouncycastle.provider)
    implementation(libs.bouncycastle.pkix)
    
    // Database
    implementation(libs.postgresql)
    
    // Encryption at rest
    implementation(libs.spring.boot.starter.actuator)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

