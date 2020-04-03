plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":gradle-examples:gradle-library"))

    testImplementation("junit:junit:4.12")
    testImplementation("nl.pvanassen:guice-junit-runner:1.1.0")
}
