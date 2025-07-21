import com.strumenta.antlrkotlin.gradle.AntlrKotlinTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.devtools.ksp") version "2.1.20-2.0.1"
    id("com.strumenta.antlr-kotlin") version "1.0.5"
    kotlin("jvm") version "2.1.20"
}

group = "sugar.lang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val generateKotlinGrammarSource = tasks.register<AntlrKotlinTask>("generateKotlinGrammarSource") {
    dependsOn("cleanGenerateKotlinGrammarSource")
    source = fileTree(layout.projectDirectory.dir("antlr")) {
        include("**/*.g4")
    }
    val pkgName = "compiler.antlr"
    packageName = pkgName
    //arguments = listOf("-visitor")
    val outDir = "generatedAntlr/${pkgName.replace(".", "/")}"
    outputDirectory = layout.buildDirectory.dir(outDir).get().asFile
}
tasks.withType<KotlinCompile> {
    dependsOn(generateKotlinGrammarSource)
}
dependencies {
    //ksp(project(":ksp"))
    implementation("com.strumenta:antlr-kotlin-runtime:1.0.5")
    implementation(fileTree("libs") { include("*.jar") })
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    //implementation("org.antlr:antlr4-runtime:4.13.2")
    //implementation("org.ow2.asm:asm:9.5")
    //implementation("org.ow2.asm:asm-util:9.5")
    implementation("com.squareup:kotlinpoet:2.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    sourceSets.main {
        kotlin.srcDir(layout.buildDirectory.dir("generatedAntlr"))
    }
    jvmToolchain(17)
}
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.RequiresOptIn",
            "-Xcontext-parameters",
            "-Xnon-local-break-continue",
            "-Xwhen-guards",
            "-Xmulti-dollar-interpolation"
        )
    }
}

