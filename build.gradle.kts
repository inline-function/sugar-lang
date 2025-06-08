import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.devtools.ksp") version "2.1.20-2.0.1"
    kotlin("jvm") version "2.1.20"
}

group = "sugar.lang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    ksp(project(":ksp"))
    implementation(fileTree("libs") { include("*.jar") })
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.antlr:antlr4-runtime:4.13.2")
    //implementation("org.ow2.asm:asm:9.5")
    //implementation("org.ow2.asm:asm-util:9.5")
    implementation("com.squareup:kotlinpoet:2.2.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.RequiresOptIn",
            "-Xcontext-parameters",
            "-Xwhen-guards",
            "-Xmulti-dollar-interpolation"
        )
    }
}