package net.meilcli.koach.gradle

object Dependencies {

    object Kotlin {

        private const val kotlinVersion = "1.3.70"
        private const val group = "org.jetbrains.kotlin"

        val gradle = Dependency(group, "kotlin-gradle-plugin", kotlinVersion)
        val stdlib = Dependency(group, "kotlin-stdlib-jdk7", kotlinVersion)
    }

    object Android {

        val gradle = Dependency("com.android.tools.build", "gradle", "3.6.2")

        val appCompat = Dependency("androidx.appcompat", "appcompat", "1.1.0")

        val junit = Dependency("androidx.test.ext", "junit", "1.1.1")
        val espresso = Dependency("androidx.test.espresso", "espresso-core", "3.2.0")
    }

    object Librarian {

        private const val group = "net.meilcli.librarian"
        private const val version = "0.8.5"

        val pluginCore = Dependency(group, "plugin-core", version)
        val pluginPreset = Dependency(group, "plugin-preset", version)
    }

    object Junit4 {

        val junit = Dependency("junit", "junit", "4.12")
    }
}