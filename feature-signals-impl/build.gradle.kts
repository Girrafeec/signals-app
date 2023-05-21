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
}

dependencies {

    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    testImplementation(project(mapOf("path" to ":feature-rescuers-api")))
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

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

    implementation(project(":core-network"))
    implementation(project(":core-preferences"))
    implementation(project(":core-base"))
    implementation(project(":event-bus"))
    implementation(project(":feature-signals-api"))
    implementation(project(":feature-location-tracker-api"))
    implementation(project(":feature-auth-api"))
}