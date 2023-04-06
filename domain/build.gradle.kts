plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
//    id("kotlin-kapt")
}

android {
    compileSdk = Versions.ANDROID_COMPILE_SDK_VERSION
    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"
            )
        }

        getByName(BuildType.DEBUG) {
        }
    }
}

dependencies {
    implementation("javax.inject:javax.inject:${Versions.JAVAX_INJECT_VERSION}")

    // Testing
    testImplementation("junit:junit:${Versions.TEST_JUNIT_VERSION}")
    testImplementation("io.mockk:mockk:${Versions.TEST_MOCKK_VERSION}")
    testImplementation("io.kotest:kotest-assertions-core:${Versions.TEST_KOTEST_VERSION}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_COROUTINES_VERSION}")

    api("com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}")
    api("com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}")
    api("com.squareup.moshi:moshi-adapters:${Versions.MOSHI_VERSION}")
    api("com.squareup.moshi:moshi-kotlin:${Versions.MOSHI_VERSION}")

    api("com.jakewharton.timber:timber:${Versions.TIMBER_LOG_VERSION}")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    api( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api( "org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")

    api ("id.zelory:compressor:3.0.1")
}
