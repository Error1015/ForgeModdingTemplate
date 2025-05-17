import org.slf4j.event.Level

plugins {
    java
    idea
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("net.neoforged.moddev")
    `maven-publish`
}

val minecraft_version: String by project
val minecraft_version_range: String by project
val neo_version: String by project
val neo_version_range: String by project
val loader_version_range: String by project
val mod_id: String by project
val mod_name: String by project
val mod_version: String by project
val mod_authors: String by project
val mod_license: String by project
val mod_description: String by project
val mod_group_id: String by project

val parchment_minecraft_version: String by project
val parchment_mappings_version: String by project

val kff_version: String by project


version = mod_version
group = mod_group_id

base {
    archivesName = mod_id
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

neoForge {
    version = neo_version

    parchment {
        mappingsVersion = parchment_mappings_version
        minecraftVersion = parchment_minecraft_version
    }

    // accessTransformers.add('src/main/resources/META-INF/accesstransformer.cfg')

    runs {
        create("client") {
            client()
        }

        create("server") {
            server()
            programArgument("--nogui")
        }

        create("gameTestServer") {
            type = "gameTestServer"
        }

        create("data") {
            data()
            programArguments.addAll(
                "--mod", mod_id, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            if (name != "data") {
                systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
            }

            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = Level.DEBUG
        }
    }
}

sourceSets.main.get().resources.srcDir("src/generated/resources")

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
    implementation("net.neoforged:neoforge:$neo_version")
    implementation("thedarkcolour:kotlinforforge-neoforge:$kff_version")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xwhen-guards") // when guards
    }
}

tasks
    .withType<ProcessResources>()
    .configureEach {
        val replaceProperties = mapOf(
            "minecraft_version" to minecraft_version, "minecraft_version_range" to minecraft_version_range, "neo_version" to neo_version, "neo_version_range" to neo_version_range,
            "loader_version_range" to loader_version_range, "mod_id" to mod_id, "mod_name" to mod_name, "mod_license" to mod_license, "mod_version" to mod_version, "mod_authors" to mod_authors,
            "mod_description" to mod_description
        )
        inputs.properties(replaceProperties)

        filesMatching(listOf("META-INF/neoforge.mods.toml")) {
            expand(replaceProperties)
        }
    }


publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            mavenLocal()
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadSources = true
    }
}