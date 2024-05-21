plugins {
    `maven-publish`
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.23"
    application
}

group = "io.github.dockyardmc"
version = "1.0"

val githubUser: String = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER")
val githubPassword: String = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")


repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/DockyardMC/Scroll")
        credentials {username = githubUser; password = githubPassword}
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.github.jglrxavpok.hephaistos:common:2.2.0")
    implementation("io.github.jglrxavpok.hephaistos:gson:2.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

application {
    mainClass.set("MainKt")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/DockyardMC/Scroll")
            credentials {username = githubUser; password = githubPassword}
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = "scroll"
            version = version
            from(components["java"])
        }
    }
}