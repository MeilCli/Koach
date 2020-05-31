package net.meilcli.koach.gradle.plugins

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.ProguardFiles
import net.meilcli.koach.gradle.Dependencies
import net.meilcli.koach.gradle.extensions.androidTestImplementation
import net.meilcli.koach.gradle.extensions.implementation
import net.meilcli.koach.gradle.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.findByName("android") as BaseExtension
        extension.compileSdkVersion(29)

        extension.defaultConfig {
            minSdkVersion(15)
            targetSdkVersion(29)
            applicationId = "net.meilcli.koach.app"
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        (extension.buildTypes.findByName("release")
            ?: extension.buildTypes.create("release")).apply {
            isMinifyEnabled = false
            proguardFiles(
                ProguardFiles.getDefaultProguardFile("proguard-android-optimize.txt", project),
                "proguard-rules.pro"
            )
        }

        extension.sourceSets.all {
            java.srcDir("src/${name}/kotlin")
        }

        project.dependencies {
            implementation(Dependencies.Kotlin.stdlib)
            implementation(Dependencies.Android.appCompat)

            testImplementation(Dependencies.Junit4.junit)

            androidTestImplementation(Dependencies.Android.espresso)
            androidTestImplementation(Dependencies.Android.junit)
        }
    }
}