plugins {
    `kotlin-dsl`
}

apply(from = "../dependencies/build.gradle")

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.github.gmazzo:gradle-buildconfig-plugin:2.0.1")
    }
}

repositories {
    jcenter()
    google()
}

val androidGradle = extra["library_Android_gradle"] as String
val bintrayPlugin = extra["library_Bintray_plugin"] as String

dependencies {
    implementation(androidGradle)
    implementation(bintrayPlugin)
}