class Dependencies {

    object Jetpack {

        object ViewModel {
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.jetapckLifecycleVersion}"
        }

        object LiveData {
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.jetapckLifecycleVersion}"
        }

        object Extensions {
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.jetapckLifecycleVersion}"
        }

    }

    object Google {

        object Material {
            const val material = "com.google.android.material:material:${Versions.materialVersion}"
        }

    }

    object AndroidX {

        object Core {
            const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        }

        object Espresso {
            const val espressoCore =
                "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
        }

        object Constraintlayout {
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        }

        object AppCompat {
            const val appCompat =
                "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        }

    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    }

    object jUnit {
        const val jUnit = "junit:junit:${Versions.jUnitVersion}"
        const val jUnitAndroidTest = "androidx.test.ext:junit:${Versions.jUnitAndroidxExtVersion}"
    }

    object Kaspresso {

    }

    object Mockito {
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.kotlinMockitoVersion}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.kotlinMockitoVersion}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit2:${Versions.retrofitVersion}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    }

    object OkHttp3 {
        const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp3Version}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp3Version}"
    }

}