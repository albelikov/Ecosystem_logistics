// Common Gradle configuration for all modules
plugins {
    id("org.springframework.boot") version "3.4.0" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "2.3.0" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "2.3.0" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

allprojects {
    // Common build configuration
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "25"
            freeCompilerArgs = listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.ExperimentalStdlibApi",
                "-Xjvm-default=all"
            )
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = "25"
        targetCompatibility = "25"
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "com.github.johnrengelman.shadow")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.0")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:2.3.0")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.0")
        }
    }

    configurations {
        all {
            exclude(group = "ch.qos.logback", module = "logback-classic")
            exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
        }
    }

    dependencies {
        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
        implementation("org.springframework.boot:spring-boot-starter-webflux")

        // Database
        implementation("org.postgresql:postgresql")
        implementation("org.flywaydb:flyway-core")
        implementation("org.flywaydb:flyway-database-postgresql")

        // Logging
        implementation("ch.qos.logback:logback-core")
        implementation("org.apache.logging.log4j:log4j-api")
        implementation("org.apache.logging.log4j:log4j-core")
        implementation("org.slf4j:slf4j-api")
        runtimeOnly("org.slf4j:log4j-over-slf4j")
        runtimeOnly("org.slf4j:jcl-over-slf4j")

        // YAML Configuration
        implementation("org.yaml:snakeyaml:2.2")

        // Testing
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
        testImplementation("com.h2database:h2")
        testImplementation("org.testcontainers:testcontainers")
        testImplementation("org.testcontainers:junit-jupiter")
        testImplementation("org.testcontainers:postgresql")
        testImplementation("org.mockito:mockito-core")
        testImplementation("org.mockito:mockito-junit-jupiter")
    }

    springBoot {
        buildInfo()
    }

    tasks.named("bootJar") {
        archiveFileName.set("${rootProject.name}-${project.name}.jar")
    }

    tasks.named("jar") {
        archiveFileName.set("${rootProject.name}-${project.name}.jar")
    }
}

// Project version
version = "1.0.0-SNAPSHOT"
group = "com.logi"
