plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
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
    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.appCompat)

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)
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

    // OSMDroid
    implementation(Dependencies.OSMDroid.osmDroid)
    implementation(Dependencies.OSMDroid.osmDroidBonuspack)

    implementation(project(":core-network"))
    implementation(project(":core-preferences"))
    implementation(project(":core-base"))
    implementation(project(":event-bus"))
}