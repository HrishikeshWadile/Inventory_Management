plugins {
//    id("com.android.application")
    id("com.google.gms.google-services")
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.inventorymanagement"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.inventorymanagement"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform(libs.firebase.bom))
//    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation(libs.firebase.analytics)
//    implementation("com.google.firebase:firebase-analytics")
    implementation (libs.recyclerview.v132)
//    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation (libs.appcompat.v161)
//    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation (libs.material.v1110)
//    implementation ("com.google.android.material:material:1.11.0")
}