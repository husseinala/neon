package com.husseinala.neon

object Versions {

    // android
    const val COMPILE_SDK = 29
    const val MIN_SDK = 21
    const val TARGET_SDK = 29

    // Kotlin
    const val KOTLIN = "1.4.0"

    // Compose
    const val COMPOSE = "1.0.0-alpha01"
    const val KOTLIN_COMPILER = "1.4.0"

    // Spotless
    const val KT_LINT = "0.38.1"
}

object Deps {

    object Kotlin {
        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    }

    object Android {
        const val GRADLE_PLUGIN = "com.android.tools.build:gradle:4.2.0-alpha07"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"
        const val MATERIAL = "com.google.android.material:material:1.1.0"
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

    const val MAVEN_PUBLISH = "com.vanniktech:gradle-maven-publish-plugin:0.11.1"

    const val GLIDE = "com.github.bumptech.glide:glide:4.11.0"
}