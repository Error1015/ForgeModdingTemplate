pluginManagement {
    repositories{
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        maven("https://maven.neoforged.net/releases")
    }


    // the plugins version you will use
    plugins {
        kotlin("jvm") version "2.1.10"
        kotlin("plugin.serialization") version "2.1.10"
        id("net.neoforged.moddev") version "2.0.88"
    }
}

rootProject.name = "NeoForgeModdingTemplate-1.21.1"