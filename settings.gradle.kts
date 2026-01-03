rootProject.name = "logistics-ecosystem"

// Core & Platform
include(":transport-platform")
include(":gis-subsystem")

// Logistic Core Services
include(":oms-service")
include(":wms-service")
include(":tms-service")
include(":fms-service")
include(":yms-service")

// Specialized & Strategic
include(":customs-service")
include(":freight-marketplace")
include(":autonomous-ops")
include(":reverse-logistics")

// Security & Compliance (SCM)
include(":scm-iam")
include(":scm-data-protection")
include(":scm-audit")
include(":cyber-resilience")

// Admin & Management
include(":admin-panel")

