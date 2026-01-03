plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    // Project modules
    implementation(project(":scm-iam"))
    implementation(project(":scm-audit"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Geospatial
    implementation("org.locationtech.jts:jts-core:1.19.0")
    implementation("org.locationtech.jts:jts-io:1.19.0")
    implementation("org.geotools:gt-shapefile:29.0")
    implementation("org.geotools:gt-epsg-hsql:29.0")
    implementation("org.geotools:gt-geojson:29.0")

    // Routing
    implementation("com.graphhopper:graphhopper:5.0")
    implementation("com.graphhopper:graphhopper-web:5.0")

    // Geocoding
    implementation("com.birdview:geocoding-client:1.0.0")

    // Tile server
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("com.h2database:h2")
}

application {
    mainClass.set("com.logi.gis.LogiGisApplication")
}

tasks.named("bootJar") {
    archiveFileName.set("logi-gis-subsystem.jar")
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
