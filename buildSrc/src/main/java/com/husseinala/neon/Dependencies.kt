package com.husseinala.neon

object Versions {

    // android
    const val COMPILE_SDK = 29
    const val MIN_SDK = 21
    const val TARGET_SDK = 29

    // Kotlin
    const val KOTLIN = "1.3.72"

    // Compose
    const val COMPOSE = "0.1.0-dev14"
    const val KOTLIN_COMPILER = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"

    // Spotless
    const val KT_LINT = "0.37.2"
}

object Deps {

    object Kotlin {
        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    }

    object Android {
        const val GRADLE_PLUGIN = "com.android.tools.build:gradle:4.2.0-alpha03"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"
        const val MATERIAL = "com.google.android.material:material:1.1.0"
    }

    object Compose {
        const val FOUNDATION = "androidx.ui:ui-foundation:${Versions.COMPOSE}"
        const val LAYOUT = "androidx.ui:ui-layout:${Versions.COMPOSE}"
        const val MATERIAL = "androidx.ui:ui-material:${Versions.COMPOSE}"
    }

    object Testing {
        const val JUNIT = "junit:junit:4.13"
    }

    object Picasso {
        const val PICASSO = "com.squareup.picasso:picasso:2.71828"
        const val TRANSFORMATIONS = "jp.wasabeef:picasso-transformations:2.2.1"
    }

    const val GLIDE = "com.github.bumptech.glide:glide:4.11.0"
}