plugins {
    kotlin("jvm") version "2.0.0" apply false
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    group = "com.logi"
    version = "1.0.0"
    
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "21"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-jvm")
    
    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        
        implementation("org.postgresql:postgresql")
        implementation("org.flywaydb:flyway-core")
        implementation("org.flywaydb:flyway-database-postgresql")
        
        implementation("ch.qos.logback:logback-classic")
        implementation("org.apache.logging.log4j:log4j-to-slf4j")
        implementation("org.slf4j:slf4j-api")
        
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("com.h2database:h2")
    }
    
    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        archiveFileName.set("${rootProject.name}-${project.name}.jar")
    }
}
