plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.alican.navigation3"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.alican.navigation3"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = "\"https://api.themoviedb.org/3/\""
        )
        buildConfigField(
            type = "String",
            name = "BASE_POSTER_URL",
            value = "\"https://image.tmdb.org/t/p/w500\""
        )
        buildConfigField(
            type = "String",
            name = "API_TOKEN",
            value = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGUzZDUxYjYwYzZiN2E3NzU3N2JkNzNmODI3MTEzOCIsInN1YiI6IjVkZmRmOGEwZDFhODkzMDAxNDg2ZjIzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8pncezOjkKsif20QbFwy4GO_1dxOt9Rfdt-EFBQ5EDE\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // ktor
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.okhttp)

    // koin

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // serialization

    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    // navigation
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)

    // material 3
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.navigation3)
    implementation(libs.material.icons)

    // coil
    implementation(libs.coil.compose)

}