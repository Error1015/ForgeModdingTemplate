subprojects {
    buildscript {
        repositories {
            maven {
                setUrl("https://repo.spongepowered.org/repository/maven-public/")
            }
            mavenCentral()
        }
        dependencies {
            classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
        }
    }

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "maven-publish")

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
}