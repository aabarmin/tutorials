plugins {
    `java-library`
    `maven-publish`
}

repositories {
    jcenter()
}

publishing {
    publications {
        create<MavenPublication>("gradle-library") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "CustomLocalRepository"
            url = uri("file://${buildDir}/repo")
        }
    }
}

dependencies {
    api("com.google.inject:guice:4.2.3")

    implementation("org.apache.commons:commons-lang3:3.10")

    testImplementation("junit:junit:4.12")
}
