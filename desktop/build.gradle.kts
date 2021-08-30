import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin(multiplatform)
    id(composePlugin)
}

group = "com.raywenderlich.findtime"
version = "1.0.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        named("jvmMain") {
            kotlin.srcDirs("src/jvmMain/kotlin")
            resources.srcDirs("src/jvmMain/resources")
            dependencies {
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)
                 // koin
                api(Deps.Koin.core)
                api(Deps.Kotlin.stdlib_common)
                api(Deps.JetBrains.uiDesktop)

                implementation(Deps.napier)
                // Coroutines
                implementation(Deps.Coroutines.common)
                implementation(project(":shared"))
                implementation(project(":composeShared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
        }
    }
}
