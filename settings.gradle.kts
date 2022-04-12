pluginManagement {
    plugins {
        id("com.github.ben-manes.versions") version "0.38.0"
        id("io.gitlab.arturbosch.detekt") version "1.16.0"
        id("io.spring.dependency-management") version "1.0.9.RELEASE"
        id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
        id("org.owasp.dependencycheck") version "6.1.5"
        id("org.sonarqube") version "3.1.1"
        id("org.springframework.boot") version "2.3.0.RELEASE"
        kotlin("jvm") version "1.4.32"
        kotlin("plugin.spring") version "1.4.32"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }
}
rootProject.name = "common-rewards"
