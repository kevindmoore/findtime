import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(multiplatform)
    kotlin(cocopods)
    id(androidLib)
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Holds Time zone information"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.JetBrains.datetime)
                // koin
                api(Deps.Koin.core)

                implementation(Deps.napier)
                // Coroutines
                implementation(Deps.Coroutines.common)
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
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdk =  Versions.compile_sdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
    }
}