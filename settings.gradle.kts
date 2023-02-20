pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "society-safety-app"
include (":app")
include(":core-base")
include(":core-network")
include(":feature-auth")
include(":core-preferences")
include(":feature-signals-map")
include(":feature-location-tracker-impl")
include(":feature-location-tracker-api")
include(":feature-sos-signal-api")
include(":feature-sos-signal-impl")
include(":feature-signals")
include(":navigation")
