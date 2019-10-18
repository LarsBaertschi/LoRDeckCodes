import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "ch.larsbaertschi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.50")

    implementation("com.google.guava", "guava", "28.1-jre")

    testCompile("io.kotlintest:kotlintest-core:3.0.2")
    testCompile("io.kotlintest:kotlintest-assertions:3.0.2")
    testCompile("io.kotlintest:kotlintest-runner-junit5:3.0.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}