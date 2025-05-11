pluginManagement {
    repositories{
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        maven {
            name = "MinecraftForge"
            setUrl("https://maven.minecraftforge.net/")
        }
        maven {
            name = "parchmentmc"
            setUrl("https://maven.parchmentmc.org")
        }
        maven("https://maven.neoforged.net/releases")
    }


    // the plugins version you will use
    plugins {
        kotlin("jvm") version "2.1.10"
        kotlin("plugin.serialization") version "2.1.10"
        id("net.minecraftforge.gradle") version "[6.0.16,6.2)"
        id("org.parchmentmc.librarian.forgegradle") version "1.+"
        id("net.neoforged.moddev") version "2.0.88"
    }
}

rootProject.name = "NeoForgeModdingTemplate-1.21.1"