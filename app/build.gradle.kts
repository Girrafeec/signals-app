plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.googleServices)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId  ="com.girrafeecstud.signals"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.appCompat)

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Unit-tests
    testImplementation(Dependencies.jUnit.jUnit)

    // UI tests
    androidTestImplementation(Dependencies.jUnit.jUnitAndroidTest)
    androidTestImplementation(Dependencies.AndroidX.Espresso.espressoCore)

    // ConstraintLayout
    implementation(Dependencies.AndroidX.Constraintlayout.constraintLayout)

    // Google Material
    implementation(Dependencies.Google.Material.material)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // Navigation
    implementation(Dependencies.Jetpack.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Jetpack.Navigation.navigationUiKtx)

    // Firebase
    implementation(platform(Dependencies.Firebase.firebaseBom))
    implementation(Dependencies.Firebase.firebaseMessaging)
    implementation(Dependencies.Firebase.firebaseAnalytics)

    // Custom views
    implementation(Dependencies.CustomViews.circleIndicator)

    // Easy permissions
    implementation(Dependencies.EasyPermissions.eazyPermissions)
    implementation(Dependencies.EasyPermissions.eazyPermissionsCoroutine)

    implementation(project(":core-base"))
    implementation(project(":core-components"))
    implementation(project(":core-network"))
    implementation(project(":core-ui"))
    implementation(project(":core-preferences"))
    implementation(project(":event-bus"))
    implementation(project(":navigation"))
    implementation(project(":feature-on-board"))
    implementation(project(":feature-auth-api"))
    implementation(project(":feature-auth-impl"))
    implementation(project(":feature-signals-map"))
    implementation(project(":feature-location-tracker-api"))
    implementation(project(":feature-location-tracker-impl"))
    implementation(project(":feature-signals-screens"))
    implementation(project(":feature-sos-signal-api"))
    implementation(project(":feature-sos-signal-impl"))
    implementation(project(":feature-rescuers-api"))
    implementation(project(":feature-rescuers-impl"))
    implementation(project(":feature-rescuers-list-api"))
    implementation(project(":feature-rescuers-list-impl"))
    implementation(project(":feature-rescuer-details-api"))
    implementation(project(":feature-rescuer-details-impl"))
    implementation(project(":feature-route-builder-api"))
    implementation(project(":feature-route-builder-impl"))
    implementation(project(":feature-signals-api"))
    implementation(project(":feature-signals-impl"))
    implementation(project(":feature-signal-details-api"))
    implementation(project(":feature-signal-details-impl"))
    implementation(project(":feature-countdown-timer-api"))
    implementation(project(":feature-countdown-timer-impl"))
    implementation(project(":feature-push-notifications-api"))
    implementation(project(":feature-push-notifications-impl"))
    implementation(project(":feature-rescuer-mode-api"))
    implementation(project(":feature-rescuer-mode-impl"))
}
