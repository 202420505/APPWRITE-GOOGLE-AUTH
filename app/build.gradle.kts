plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "me.moontree.test.appwrite.auth"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("io.appwrite:sdk-for-android:3.0.2")
}
