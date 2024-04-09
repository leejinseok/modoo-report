dependencies {
    implementation(project(":modoo-report-core"))
    implementation("org.springframework.boot:spring-boot-starter-batch")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

}

tasks.withType<Test> {
    useJUnitPlatform()
}