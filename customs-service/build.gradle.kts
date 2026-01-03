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
    
    // EDI/XML Processing (EDIFACT, XML, ACE integration)
    implementation(libs.bundles.xml.processing)
    
    // Security for government integrations
    implementation(libs.spring.boot.starter.security)
    implementation(libs.bouncycastle.provider)
    implementation(libs.bouncycastle.pkix)
    
    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.postgresql)
}

