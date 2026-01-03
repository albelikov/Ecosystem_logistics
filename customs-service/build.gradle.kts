plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Project modules
    implementation(project(":scm-iam"))
    implementation(project(":scm-audit"))
    implementation(project(":scm-data-protection"))
    implementation(project(":tms-service"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // PDF generation for customs declarations
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.itextpdf:itext7-core:8.0.5")

    // Electronic document exchange
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.integration:spring-integration-xml")

    // Integration with customs APIs
    implementation("org.springframework.boot:spring-boot-starter-soap")
    implementation("org.springframework.ws:spring-ws-security")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("com.h2database:h2")
}

application {
    mainClass.set("com.logi.customs.LogiCustomsApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-customs-service.jar")
}

tasks.named("compileKotlin") {
    kotlinOptions {
        jvmTarget = "25"
        freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.ExperimentalStdlibApi"
        )
    }
}

springBoot {
    buildInfo()
}
