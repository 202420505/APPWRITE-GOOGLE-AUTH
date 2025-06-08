import org.gradle.api.JavaVersion // 올바른 JavaVersion 패키지 import

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "me.moontree.test.appwrite.auth.app2"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.moontree.test.appwrite.auth.app2"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // 올바른 JavaVersion 참조
        targetCompatibility = JavaVersion.VERSION_17 // 올바른 JavaVersion 참조
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true  // ✅ 이 방식이 Kotlin DSL에서 더 안정적
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // 올바른 toolchain 설정
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("io.appwrite:sdk-for-android:8.1.0")
}
