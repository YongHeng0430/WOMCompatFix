plugins {
    id("net.minecraftforge.gradle") version "6.0.+"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
    id("org.spongepowered.mixin") version "0.7.+"
}

version = "1.0.0"
group = "com.yourname.womfix"

base {
    archivesName.set("womfix")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

minecraft {
    mappings("parchment", "2023.09.03-1.20.1")

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("womfix") {
                    source(sourceSets.main.get())
                }
            }
        }

        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("womfix") {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

sourceSets.main.get().resources {
    srcDir("src/generated/resources")
}

dependencies {
    minecraft("net.minecraftforge:forge:1.20.1-47.4.9")

    // Mixin
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

mixin {
    add(sourceSets.main.get(), "womfix.refmap.json")
    config("mixins.womfix.json")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Specification-Title" to "womfix",
            "Specification-Vendor" to "yourname",
            "Specification-Version" to "1",
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "yourname",
            "Implementation-Timestamp" to java.time.Instant.now().toString(),
            "MixinConfigs" to "mixins.womfix.json"
        )
    }
    finalizedBy("reobfJar")
}
