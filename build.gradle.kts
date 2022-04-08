plugins {
    kotlin("jvm") version "1.6.20"
    id("fabric-loom") version "0.10.65"
    java
}

group = "com.lderic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    implementation(kotlin("stdlib"))

    minecraft("com.mojang:minecraft:1.16.5")
    mappings("net.fabricmc:yarn:1.16.5+build.10")
    modImplementation("net.fabricmc:fabric-loader:0.12.12")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}