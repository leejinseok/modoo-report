dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2")

    // Querydsl 추가
    implementation("com.querydsl:querydsl-jpa:${dependencyManagement.importedProperties.get("querydsl.version")}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties.get("querydsl.version")}:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
}

tasks.withType<Test> {
    useJUnitPlatform()
}