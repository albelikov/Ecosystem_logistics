plugins {
    id("logistics.service")
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Spring Boot
    implementation(libs.bundles.spring.web)
    implementation(libs.spring.boot.starter.data.jpa)
    
    // Security & IAM
    implementation(libs.bundles.spring.security.bundle)
    implementation(libs.bundles.jwt.bundle)
    
    // Database
    implementation(libs.postgresql)
    
    // FIPS-compliant cryptography
    implementation(libs.bouncycastle.provider)
    implementation(libs.bouncycastle.pkix)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

