plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
    buildFeatures {
        viewBinding = true
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
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Unit-tests
    testImplementation(Dependencies.jUnit.jUnit)
    testImplementation(Dependencies.Mockito.mockitoKotlin)
    testImplementation(Dependencies.Mockito.mockitoInline)
    testImplementation(Dependencies.OkHttp3.mockWebServer)
    testImplementation(Dependencies.Coroutines.coroutinesTest)

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

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)

    // ViewModel, LiveData
    implementation(Dependencies.Jetpack.ViewModel.viewModel)
    implementation(Dependencies.Jetpack.LiveData.liveData)

    // Navigation
    implementation(Dependencies.Jetpack.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Jetpack.Navigation.navigationUiKtx)

    implementation(project(":core-ui"))
    implementation(project(":core-base"))
    implementation(project(":event-bus"))
    implementation(project(":navigation"))
    implementation(project(":feature-signals-api"))
    implementation(project(":feature-signal-details-api"))
    implementation(project(":feature-auth-api"))
}