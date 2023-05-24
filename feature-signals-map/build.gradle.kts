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
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

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

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverterGson)

    // OkHttp3
    implementation(Dependencies.OkHttp3.okHttp3)

    // ViewModel, LiveData
    implementation(Dependencies.Jetpack.ViewModel.viewModel)
    implementation(Dependencies.Jetpack.LiveData.liveData)

    // Navigation
    implementation(Dependencies.Jetpack.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Jetpack.Navigation.navigationUiKtx)

    // Room
//    implementation(Dependencies.Jetpack.Room.room)
//    kapt(Dependencies.Jetpack.Room.roomCompiler)
//    implementation(Dependencies.Jetpack.Room.roomKtx)

    // EasyPermissions
    implementation(Dependencies.EasyPermissions.easyPermissions)

    // OSMDroid
    implementation(Dependencies.OSMDroid.osmDroid)
    implementation(Dependencies.OSMDroid.osmDroidBonuspack)

    // Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.glideCompiler)

    implementation(project(":core-ui"))
    implementation(project(":core-network"))
    implementation(project(":core-components"))
    implementation(project(":core-preferences"))
    implementation(project(":core-base"))
    implementation(project(":event-bus"))
    implementation(project(":navigation"))
    implementation(project(":feature-on-board"))
    implementation(project(":feature-route-builder-api"))
    implementation(project(":feature-location-tracker-api"))
    implementation(project(":feature-sos-signal-api"))
    implementation(project(":feature-rescuers-api"))
    implementation(project(":feature-rescuers-list-api"))
    implementation(project(":feature-rescuer-details-api"))
    implementation(project(":feature-signals-api"))
    implementation(project(":feature-signal-details-api"))
    implementation(project(":feature-push-notifications-api"))
    implementation(project(":feature-rescuer-mode-api"))
}
