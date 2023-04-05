plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    namespace = "com.girrafeecstud.countdown_timer_impl"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.appCompat)

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)

    // Unit-tests
    testImplementation(Dependencies.jUnit.jUnit)
    testImplementation(Dependencies.Mockito.mockitoKotlin)
    testImplementation(Dependencies.Mockito.mockitoInline)
    testImplementation(Dependencies.Coroutines.coroutinesTest)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)

    implementation(project(":core-base"))
    implementation(project(":feature-countdown-timer-api"))
}