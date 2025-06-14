import org.spongepowered.asm.gradle.plugins.MixinExtension
import java.text.SimpleDateFormat
import java.util.*

buildscript {
    repositories {
        maven("https://repo.spongepowered.org/repository/maven-public/")
        mavenCentral()
    }

    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
    }
}

plugins {
    java
    idea
    `maven-publish`
    id("net.minecraftforge.gradle")
    id("org.parchmentmc.librarian.forgegradle")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

apply(plugin = "org.spongepowered.mixin")

val minecraft_version: String by project
val minecraft_version_range: String by project
val forge_version: String by project
val forge_version_range: String by project
val loader_version_range: String by project
val mod_id = property("mod_id") as String
val mod_name: String by project
val mod_version: String by project
val mod_authors: String by project
val mod_license: String by project
val mod_description: String by project
val mod_group_id: String by project

val mapping_channel: String by project
val mapping_version: String by project

val kff_version: String by project

group = mod_group_id
version = mod_version

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://thedarkcolour.github.io/KotlinForForge/") {
        name = "Kotlin For Forge Maven"
        content {
            includeGroup("thedarkcolour")
        }
    }

    maven("https://www.cursemaven.com") {
        content {
            includeGroup("curse.maven")
        }
    }
}

dependencies {
    minecraft("net.minecraftforge:forge:${minecraft_version}-${forge_version}")
    implementation("thedarkcolour:kotlinforforge:${kff_version}")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    compilerOptions {
        freeCompilerArgs.add("-Xwhen-guards")
    }
}


minecraft {
    mappings(mapping_channel, mapping_version)
    copyIdeResources = true

    runs {
        configureEach {
            source(sourceSets.main.get())
        }

        create("client") {
            workingDirectory(file("run"))
            properties(
                mapOf(
                    "forge.logging.markers" to "REGISTRIES", "forge.logging.console.level" to "debug"
                )
            )
        }

        create("server") {
            workingDirectory(file("run/server"))
            args("--nogui")
            property("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("gameTestServer") {
            workingDirectory(file("run/server"))
            property("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("data") {
            workingDirectory(file("run"))
            args("--mod", mod_id, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
        }
    }
}

configure<MixinExtension> {
    add(sourceSets["main"], "$mod_id.refmap.json")
    config("$mod_id.mixins.json")
}

sourceSets.main.get().resources.srcDir("src/generated/resources")


tasks
    .withType<ProcessResources>()
    .configureEach {
        val replaceProperties = mapOf(
            "minecraft_version" to minecraft_version, "minecraft_version_range" to minecraft_version_range, "forge_version" to forge_version, "forge_version_range" to forge_version_range,
            "loader_version_range" to loader_version_range, "mod_id" to mod_id, "mod_name" to mod_name, "mod_license" to mod_license, "mod_version" to mod_version, "mod_authors" to mod_authors,
            "mod_description" to mod_description
        )
        inputs.properties(replaceProperties)

        filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
            expand(replaceProperties)
        }
    }


tasks.named<Jar>("jar") {
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to mod_id, "Specification-Vendor" to mod_authors, "Specification-Version" to "1", "Implementation-Title" to project.name,
                "Implementation-Version" to archiveVersion.get(), "Implementation-Vendor" to mod_authors,
                "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT).format(Date()),
                "MixinConfigs" to "${mod_id}.mixins.json" // Added this, You must name mixin config as "modid.mixins.json"
            )
        )
    }
    finalizedBy("reobfJar")
}

tasks
    .withType<JavaCompile>()
    .configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }


idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}