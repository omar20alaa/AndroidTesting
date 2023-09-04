plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "app.android_unit_testing"
    compileSdk = 33

    defaultConfig {
        applicationId = "app.android_unit_testing"
        minSdk = 24
        targetSdk = 33
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation ("androidx.activity:activity-ktx:1.3.1")

    // GSON
    implementation ("com.google.code.gson:gson:2.8.8")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // dagger hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-android-compiler:2.47")

    // testing
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:3.9.0")
    testImplementation ("org.mockito:mockito-inline:3.9.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation ("org.mockito:mockito-core:3.9.0")
    testImplementation ("io.mockk:mockk:1.12.0")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.4.0")
}