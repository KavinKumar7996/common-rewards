import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

description = "common-rewards"

plugins {
    base
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt")
    id("io.spring.dependency-management")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.owasp.dependencycheck")
    id("org.sonarqube")
    id("org.springframework.boot") apply false
    jacoco
    java
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "th.co.the1.common"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

jacoco {
    toolVersion = "0.8.6"
}

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
    maven(url = "https://plugins.gradle.org/m2/")
}

extra["springCloudVersion"] = "Hoxton.SR6"

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.vladmihalcea:hibernate-types-52:2.10.4")
    implementation("io.github.openfeign.form:feign-form-spring:3.8.0")
    implementation("io.github.openfeign.form:feign-form:3.8.0")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:3.1.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("net.logstash.logback:logstash-logback-encoder:6.4")
    implementation("net.postgis:postgis-jdbc:2.2.0") {
        exclude(module = "postgresql")
    }
    implementation("net.sf.dozer:dozer:5.5.1")
    implementation("org.hibernate:hibernate-spatial")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.liquibase:liquibase-core:3.8.2")
    implementation("org.liquibase:liquibase-gradle-plugin:2.0.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock")
    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.valiktor:valiktor-javatime:0.11.0")
    implementation("org.valiktor:valiktor-spring-boot-starter:0.11.0")
    implementation("de.qaware.tools.collection-cacheable-for-spring:collection-cacheable-for-spring-starter:1.2.0")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-junit-jupiter:2.21.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    var itags: String? = System.getProperty("includeTags")
    var etags: String? = System.getProperty("excludeTags")
    if (itags.isNullOrBlank() && etags.isNullOrBlank()) {
        etags = "slow"
    }
    useJUnitPlatform {
        itags?.let { includeTags(itags) }
        etags?.let { excludeTags(etags) }
    }
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    setForkEvery(100)
}

tasks.register<Test>("test-all") {
    useJUnitPlatform()
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    setForkEvery(100)
}

tasks.withType<JavaCompile> {
    options.isFork = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

configure<DetektExtension> {
    buildUponDefaultConfig = true
    input = files("src/main/kotlin", "src/test/kotlin")
    config = files("detekt.yml")
    reports {
        xml {
            enabled = false
        }
        html {
            enabled = true
        }
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

fun excludeTestFiles(): FileTree {
    return sourceSets.main.get().output.asFileTree.matching {
        exclude("**/exception/*")
        exclude("**/*ApplicationKt.class")
        exclude("**/*Config*.class")
        exclude("**/*Mock*.class")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("$buildDir/jacocoHtml")
    }
    classDirectories.setFrom(excludeTestFiles())
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.0".toBigDecimal()
            }
        }
    }
    classDirectories.setFrom(excludeTestFiles())
}

tasks.check {
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
    val jacocoTestReport = tasks.findByName("jacocoTestReport")
    jacocoTestReport?.mustRunAfter(tasks.findByName("test"))
    tasks.findByName("jacocoTestCoverageVerification")?.mustRunAfter(jacocoTestReport)
}

tasks.register("version") {
    println(project.version)
}
