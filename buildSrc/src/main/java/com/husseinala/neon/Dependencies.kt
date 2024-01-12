package com.husseinala.neon

object Versions {

    // android
    const val COMPILE_SDK = 34
    const val MIN_SDK = 21
    const val TARGET_SDK = 34

    // Kotlin
    const val KOTLIN = "1.9.20"
    const val DOKKA = "1.9.10"

    // Compose
    const val COMPOSE = "1.5.4"
    const val COMPOSE_COMPILER = "1.5.5"

    // Spotless
    const val KT_LINT = "1.1.1"
}

object Deps {

    object Kotlin {
        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        const val DOKKA = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.DOKKA}"
    }

    object Android {
        const val GRADLE_PLUGIN = "com.android.tools.build:gradle:8.2.1"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.2"
        const val MATERIAL = "com.google.android.material:material:1.3.0"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.3.0-alpha02"
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
        const val PICASSO = "com.squareup.picasso:picasso:2.8"
        const val TRANSFORMATIONS = "jp.wasabeef:picasso-transformations:2.4.0"
    }

    const val MAVEN_PUBLISH = "com.vanniktech:gradle-maven-publish-plugin:0.27.0"

    const val GLIDE = "com.github.bumptech.glide:glide:4.12.0"
}