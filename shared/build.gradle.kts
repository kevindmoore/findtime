import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(multiplatform)
    kotlin(cocopods)
    id(androidLib)
}

version = "1.0.0"

kotlin {
    android()
    jvm("desktop")

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

//    iosTarget("ios") {}

    cocoapods {
        summary = "Holds Time zone information"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
//        all {
//            languageSettings.apply {
//                useExperimentalAnnotation("kotlin.RequiresOptIn")
//                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
//            }
//        }
        val commonMain by getting {
            kotlin.srcDirs("src/commonMain/kotlin")
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                implementation(Deps.JetBrains.datetime)
                // koin
                api(Deps.Koin.core)

                implementation(Deps.napier)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Deps.KotlinTest.common)
                implementation(Deps.KotlinTest.annotations)
            }
        }
        val androidMain by getting

        val androidTest by getting {
            dependencies {
                implementation(kotlin(Deps.KotlinTest.junit))
                implementation(Deps.Test.junit)
            }
        }

        val desktopMain by getting {
            dependsOn(commonMain)
        }
//        val iosMain by getting
//        val iosTest by getting
    }
}

android {
    compileSdk =  Versions.compile_sdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
    dependencies {
        implementation(Deps.Compose.runtime)
    }
}