package jp.dely.koach.gradle.extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}