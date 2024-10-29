plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt") // Correct way to add kapt in build.gradle.kts
}

android {
    namespace = "com.yanz.projectkuliah"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yanz.projectkuliah"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core KTX and AppCompat
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Material 3 and Compose libraries
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)

    // Compose BOM to manage consistent Compose versions
    implementation(platform(libs.androidx.compose.bom))

    // UI and tooling dependencies
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Lifecycle and ViewModel Compose
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Activity Compose
    implementation(libs.androidx.activity.compose)

    // Retrofit and Gson for API calls and JSON parsing
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.androidx.navigation.compose)

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1") // Room runtime
    implementation("androidx.room:room-ktx:2.6.1") // Room Kotlin extensions
    kapt("androidx.room:room-compiler:2.6.1") // Room annotation processor

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug dependencies
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}
