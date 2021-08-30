const val androidPlugin = "android"
const val androidApp = "com.android.application"
const val androidLib = "com.android.library"
const val multiplatform = "multiplatform"
const val composePlugin = "org.jetbrains.compose"
const val cocopods = "native.cocoapods"

object Versions {
    const val min_sdk = 24
    const val target_sdk = 30
    const val compile_sdk = 30

    const val kotlin = "1.5.21"
    const val kotlin_gradle_plugin = "1.5.21"
    const val android_gradle_plugin = "7.1.0-alpha09"
    const val desktop_compose_plugin = "1.0.0-alpha3"
    const val compose_version= "1.1.0-alpha01"

    const val coroutines = "1.5.0-native-mt"
    const val koin = "3.1.1"
    const val junit = "4.13.2"
    const val material = "1.3.0"
    const val kotlinxDateTime = "0.2.1"
    const val activity_compose = "1.3.0-beta01"
    const val datetime = "0.2.1"
    const val nav_compose = "2.4.0-alpha01"
    const val napier = "1.4.1"
    const val junit5 = "1.5.10"
    const val frameworkName = "shared"
    const val coil= "0.8.1"
}

object Deps {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin}"
    const val desktop_compose_gradle_plugin = "org.jetbrains.compose:compose-gradle-plugin:${Versions.desktop_compose_plugin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
    const val napier = "io.github.aakira:napier:${Versions.napier}"

    object Kotlin {
        const val stdlib_common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val stdlib8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object Koin {
        val core = "io.insert-koin:koin-core:${Versions.koin}"
        val jvm = "io.insert-koin:koin-core-jvm:${Versions.koin}"
        val test = "io.insert-koin:koin-test:${Versions.koin}"
        val android = "io.insert-koin:koin-android:${Versions.koin}"
        val compose = "io.insert-koin:koin-androidx-compose:3.0.1"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose_version}"
        const val uiUtil = "androidx.compose.ui:ui-util:${Versions.compose_version}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
        const val material = "androidx.compose.material:material:${Versions.compose_version}"
        const val material_icons = "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose_version}"
        const val compiler = "androidx.compose.compiler:compiler:${Versions.compose_version}"
        const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose_version}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
        const val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
    }

    object Coil {
        const val coil = "com.google.accompanist:accompanist-coil:${Versions.coil}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.coil}"
        const val swipe = "com.google.accompanist:accompanist-swiperefresh:${Versions.coil}"

    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        const val junit5framework = "org.jetbrains.kotlin:kotlin-test-framework-impl:${Versions.junit5}"
        const val junit5 = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.junit5}"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object JetBrains {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
        const val uiDesktop = "org.jetbrains.compose.ui:ui-desktop:${Versions.desktop_compose_plugin}"
        const val uiUtil = "org.jetbrains.compose.ui:ui-util:${Versions.desktop_compose_plugin}"
    }
}
