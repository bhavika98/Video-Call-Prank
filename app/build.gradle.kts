@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKSP)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val applovinApiKey: String = "API_KEY"

android {
    namespace = "com.app.incroyable.videocall_prank"
    compileSdk = 34

    signingConfigs {
        create("release") {
            storeFile = file("JKS Path")
            storePassword = "incroyable"
            keyAlias = "Incroyable Apps"
            keyPassword = "incroyable"
        }
    }

    defaultConfig {
        applicationId = "com.app.incroyable.videocall_prank"
        minSdk = 24
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
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.material.icon)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.landscapist.glide)

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.runtime.livedata)

    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.androidx.activity.compose.v140)
    implementation(libs.dexter)
    implementation(libs.prdownloader)
    implementation(libs.lifecycle.viewmodel.compose)

    implementation(project(":exomedia"))
    implementation(libs.shimmer)
    implementation(libs.androidx.ui.viewbinding)

    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // Firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    // Applovin
    implementation(libs.adjust.android)
    implementation(libs.applovin.sdk)
    implementation(libs.facebook.adapter)

    // AppOpen
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.common.java8)

    // Facebook
    implementation(libs.androidx.annotation)
    implementation(libs.facebook)
    implementation(libs.audience.network.sdk)

    // Admob
    implementation(libs.play.services.ads)
}
apply(plugin = "com.google.gms.google-services")