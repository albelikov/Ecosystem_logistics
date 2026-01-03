rootProject.name = "logi"

include(
    "admin-panel",
    "autonomous-ops",
    "customs-service",
    "cyber-resilience",
    "fms-service",
    "freight-marketplace",
    "gis-subsystem",
    "oms-service",
    "reverse-logistics",
    "scm-audit",
    "scm-data-protection",
    "scm-iam",
    "tms-service",
    "transport-platform",
    "wms-service",
    "yms-service"
)

// Enable parallel resolution
settingsEvaluated { settings ->
    settings.pluginManagement {
        repositories {
            gradlePluginPortal()
            mavenCentral()
            google()
        }
    }
}

gradle.projectsLoaded {
    rootProject.buildFileName = 'build.gradle.kts'
}
