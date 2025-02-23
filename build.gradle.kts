plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation("org.telegram:telegrambots-client:8.2.0")
    implementation("org.telegram:telegrambots-longpolling:8.2.0")

    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.slf4j:slf4j-api:2.0.16")

    implementation("io.github.cdimascio:dotenv-java:3.1.0")

    implementation("org.hibernate.orm:hibernate-core:6.6.8.Final")
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation("org.liquibase:liquibase-core:4.31.0")

}

tasks.test {
    useJUnitPlatform()
}