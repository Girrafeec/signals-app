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
        jcenter()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
rootProject.name = "signals"
include (":app")
include(":core-base")
include(":core-network")
include(":feature-auth-api")
include(":feature-auth-impl")
include(":core-preferences")
include(":feature-signals-map")
include(":feature-location-tracker-impl")
include(":feature-location-tracker-api")
include(":feature-sos-signal-api")
include(":feature-sos-signal-impl")
include(":feature-signals-screens")
include(":navigation")
include(":event-bus")
include(":feature-rescuers-api")
include(":feature-rescuers-impl")
include(":feature-rescuers-list-api")
include(":feature-rescuers-list-impl")
include(":feature-rescuer-details-api")
include(":feature-rescuer-details-impl")
include(":feature-route-builder-api")
include(":feature-route-builder-impl")
include(":core-ui")
include(":feature-signals-api")
include(":feature-signals-impl")
include(":feature-signal-details-api")
include(":feature-signal-details-impl")
include(":feature-countdown-timer-api")
include(":feature-countdown-timer-impl")
include(":core-components")
include(":feature-on-board")
include(":feature-push-notifications-api")
include(":feature-push-notifications-impl")
include(":feature-rescuer-mode-api")
include(":feature-rescuer-mode-impl")
