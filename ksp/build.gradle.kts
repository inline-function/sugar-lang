plugins {
    kotlin("jvm") version "2.1.20"
}

group = "sugar.lang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "2.1.20"))
    }
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.1.20-2.0.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}