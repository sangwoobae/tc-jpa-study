plugins {
    id("org.springframework.boot") version "2.2.2.RELEASE"

    kotlin("jvm") version "1.3.61"

    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    // kotlin("plugin.allopen") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    // kotlin("plugin.noarg") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.61"
    idea
}

apply {
    plugin("io.spring.dependency-management")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.tc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testRuntimeOnly("com.h2database:h2")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    test {
        useJUnitPlatform()
    }
}
