package com.husseinala.neon

object Versions {

    // android
    const val COMPILE_SDK = 29
    const val MIN_SDK = 21
    const val TARGET_SDK = 29

    // Kotlin
    const val KOTLIN = "1.4.10"
    const val DOKKA = "1.4.0"

    // Compose
    const val COMPOSE = "1.0.0-alpha03"
    const val KOTLIN_COMPILER = "1.4.10"

    // Spotless
    const val KT_LINT = "0.39.0"
}

object Deps {

    object Kotlin {
        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        const val DOKKA = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.DOKKA}"
    }

    object Android {
        const val GRADLE_PLUGIN = "com.android.tools.build:gradle:4.2.0-alpha11"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.1"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
        const val MATERIAL = "com.google.android.material:material:1.2.1"
    }

    object Compose {
        const val FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
        const val LAYOUT = "androidx.compose.foundation:foundation-layout:${Versions.COMPOSE}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
    }

    object Testing {
        const val JUNIT = "junit:junit:4.13"
    }

    object Picasso {
        const val PICASSO = "com.squareup.picasso:picasso:2.71828"
        const val TRANSFORMATIONS = "jp.wasabeef:picasso-transformations:2.2.1"
    }

    const val MAVEN_PUBLISH = "com.vanniktech:gradle-maven-publish-plugin:0.13.0"

    const val GLIDE = "com.github.bumptech.glide:glide:4.11.0"
}