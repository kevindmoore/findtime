import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id(androidLib)
    kotlin(multiplatform)
    id(composePlugin)
}

version = "1.0.0"

kotlin {
    android()
    jvm() {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
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
            dependencies {
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)
                implementation(Deps.JetBrains.datetime)
                // koin
                api(Deps.Koin.core)

                implementation(Deps.napier)
                implementation(project(":shared"))
            }
        }
//        val desktopMain by getting {
//            dependsOn(commonMain)
//        }
        named("jvmMain") {
            kotlin.srcDirs("src/jvmMain/kotlin")
//            dependsOn(commonMain)
        }
    }
}

android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
}