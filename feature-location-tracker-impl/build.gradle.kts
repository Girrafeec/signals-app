plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
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

    // ViewModel, LiveData, LifecycleService
    implementation(Dependencies.Jetpack.ViewModel.viewModel)
    implementation(Dependencies.Jetpack.LiveData.liveData)
    implementation(Dependencies.Jetpack.LifecycleService.lifecycleService)

    // EasyPermissions
    implementation(Dependencies.EasyPermissions.easyPermissions)

    // GMS
    implementation(Dependencies.Google.GMS.gmsLocation)

    implementation(project(":core-network"))
    implementation(project(":core-preferences"))
    implementation(project(":core-base"))
    implementation(project(":feature-location-tracker-api"))
}