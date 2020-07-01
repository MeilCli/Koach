package jp.dely.koach.gradle.plugins

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import jp.dely.koach.gradle.extensions.androidTestImplementation
import jp.dely.koach.gradle.extensions.implementation
import jp.dely.koach.gradle.extensions.testImplementation
import jp.dely.koach.gradle.dependencies.Android
import jp.dely.koach.gradle.dependencies.Junit4
import jp.dely.koach.gradle.dependencies.Kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.findByName("android") as BaseExtension
        extension.compileSdkVersion(29)

        extension.defaultConfig {
            minSdkVersion(15)
            targetSdkVersion(29)
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFile("consumer-rules.pro")
        }

        (extension.buildTypes.findByName("release")
            ?: extension.buildTypes.create("release")).apply {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt", project.layout),
                "proguard-rules.pro"
            )
        }

        extension.sourceSets.all {
            java.srcDir("src/${name}/kotlin")
        }

        project.dependencies {
            implementation(Kotlin.stdlib)
            implementation(Android.appCompat)

            testImplementation(Junit4.junit)

            androidTestImplementation(Android.espresso)
            androidTestImplementation(Android.junit)
        }
    }
}