plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Project modules
    implementation(project(":scm-iam"))
    implementation(project(":scm-audit"))
    implementation(project(":scm-data-protection"))

    // Admin specific
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // UI Libraries
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("com.github.admin4j:admin-spring-boot-starter:0.4.7")

    // Webjars for static resources
    implementation("org.webjars:bootstrap:5.3.3")
    implementation("org.webjars:jquery:3.7.1")
    implementation("org.webjars:font-awesome:6.5.2")

    // Chart libraries
    implementation("org.webjars.npm:chart.js:4.4.1")

    // DataTables
    implementation("org.webjars:datatables:2.1.5")
    implementation("org.webjars:datatables-bootstrap5:2.0.2")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.h2database:h2")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

application {
    mainClass.set("com.logi.admin.LogiAdminApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-admin-panel.jar")
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
