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
    }


    // the plugins version you will use
    plugins {
        kotlin("jvm") version "2.1.10"
        kotlin("plugin.serialization") version "2.1.10"
        id("net.minecraftforge.gradle") version "[6.0.16,6.2)"
        id("org.parchmentmc.librarian.forgegradle") version "1.+"
    }
}

rootProject.name = "ForgeModdingTemplate-1.20.1"

include("Forge-1.20.1")