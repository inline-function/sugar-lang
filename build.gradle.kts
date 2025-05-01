plugins {
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
    kotlin("jvm") version "2.1.20"
}

group = "sugar.lang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    ksp(project(":ksp"))
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.antlr:antlr4-runtime:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}