buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.androidGradlePlugin)
        classpath(Plugins.kotlinGradlePlugin)
        classpath(Plugins.navigationSafeArgsPlugin)
        classpath(Plugins.googleServicesPlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}