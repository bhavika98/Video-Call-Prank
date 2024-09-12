buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://artifacts.applovin.com/android") }
    }
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("com.applovin.quality:AppLovinQualityServiceGradlePlugin:+")
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinKSP) apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.firebase.crashlytics") version "2.9.6" apply false
}
true