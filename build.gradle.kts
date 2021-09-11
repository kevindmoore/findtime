buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath(Deps.desktop_compose_gradle_plugin)
        classpath(Deps.android_gradle_plugin)
        classpath(Deps.kotlin_gradle_plugin)
    }
}

gradle.taskGraph.whenReady {
    tasks
        .filter { it.hasProperty("duplicatesStrategy") } // Because it's some weird decorated wrapper that I can't cast.
        .forEach {
            println("${it}")
            it.setProperty("duplicatesStrategy", "EXCLUDE")
        }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//        this.duplicatesStrategy = DuplicatesStrategy.INCLUDE
        kotlinOptions {
            freeCompilerArgs += listOf(
                "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
                "-Xopt-in=org.koin.core.component.KoinApiExtension",
                "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-Xopt-in=kotlin.RequiresOptIn",
            )
        }
    }
}

subprojects {
    afterEvaluate {
//        tasks.named<org.gradle.jvm.tasks.Jar>("jvmProcessResources") {
//            duplicatesStrategy = DuplicatesStrategy.INCLUDE
//        }
        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { ext ->
            ext.sourceSets.removeAll { sourceSet ->
                setOf(
                    "androidAndroidTestRelease",
                    "androidTestFixtures",
                    "androidTestFixturesDebug",
                    "androidTestFixturesRelease",
                ).contains(sourceSet.name)
            }
        }
    }
}