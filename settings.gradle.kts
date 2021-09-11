pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "Find_Time"
include(":androidApp")
include(":shared")
include(":shared-ui")
include(":desktop")
