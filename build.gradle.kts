plugins {
    kotlin("jvm") version "2.1.20"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.21.0")
    implementation("org.seleniumhq.selenium:selenium-devtools-v123:4.21.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    implementation("io.github.bonigarcia:webdrivermanager:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.qameta.allure:allure-junit5:2.25.0")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs(
        "-XX:+EnableDynamicAgentLoading",
        "-Xshare:off"
    )
}

