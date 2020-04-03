plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    api("com.google.inject:guice:4.2.3")

    implementation("org.apache.commons:commons-lang3:3.10")

    testImplementation("junit:junit:4.12")
}
