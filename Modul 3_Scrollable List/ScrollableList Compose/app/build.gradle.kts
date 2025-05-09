plugins {
    alias(libs.plugins.android.application) // Alias dari version catalog
    alias(libs.plugins.kotlin.android) // Alias dari version catalog
    alias(libs.plugins.kotlin.compose) // Alias plugin Compose dari version catalog
}

android {
    namespace = "com.example.scrollablelist"
    compileSdk = 35 // SDK yang stabil

    defaultConfig {
        applicationId = "com.example.scrollablelist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5" // Gunakan versi terbaru dari Compose Compiler
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM untuk kontrol versi yang konsisten
    implementation(platform(libs.androidx.compose.bom)) // Menggunakan BOM Compose untuk mengelola versi otomatis
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Navigation Component untuk Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Coil untuk load image
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Memastikan testing sesuai dengan BOM Compose
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
