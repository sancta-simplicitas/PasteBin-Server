group = "com.github.sancta-simplicitas.PasteBin-Server"
version = "1.0-SNAPSHOT"


plugins {
    scala
    application
}


application {
    mainClassName = "com.github.sancta-simplicitas.pastebin.server"
}

repositories {
    mavenCentral()
}

val vertxVersion = "3.8.4"
dependencies {
    implementation("org.scala-lang:scala-library:2.13.1")
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("org.redisson:redisson:3.11.6")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
