plugins {
    id("java")
    id("java-test-fixtures")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.adarshr.test-logger") version "4.0.0"
}

allprojects {
    group = "com.rainyheaven.moddo-report"
    version = "0.0.1-SNAPSHOT"

    apply {
        plugin("java")
        plugin("java-test-fixtures")
        plugin("com.adarshr.test-logger")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
    }
}

val apiProject = project(":modoo-report-api")
val batchProject = project(":modoo-report-batch")
val coreProject = project(":modoo-report-core")

val springProjects = listOf(apiProject, batchProject, coreProject)

configure(springProjects) {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }
}
