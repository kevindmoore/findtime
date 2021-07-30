plugins {
    id(androidApp)
    kotlin(androidPlugin)
}

dependencies {
    implementation(project(":shared"))
    with(Deps) {
        implementation(napier)
        implementation(material)
    }

    implementation(Deps.Kotlin.stdlib)

    // Coroutines
    with(Deps.Coroutines) {
        implementation(common)
        implementation(android)
    }

    // Koin
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    //Compose
    with(Deps.Compose) {
        implementation(runtime)
        implementation(runtime_livedata)
        implementation(ui)
        implementation(tooling)
        implementation(foundation)
        implementation(foundationLayout)
        implementation(material)
        implementation(material_icons)
        implementation(activity)
        implementation(navigation)
    }
    with(Deps.Coil) {
        implementation(coil)
        implementation(insets)
        implementation(swipe)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-Xopt-in=org.koin.core.component.KoinApiExtension",
            "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi"
        )
    }
}
android {
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "com.raywenderlich.findtime.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }

}