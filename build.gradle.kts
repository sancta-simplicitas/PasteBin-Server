group = "com.github.sancta-simplicitas.PasteBin-Server"
version = "1.0-SNAPSHOT"

plugins {
    scala
    application
}

application {
    mainClassName = "com.github.sancta.simplicitas.pastebin.App"
}

repositories {
    mavenCentral()
}

val vertxVersion = "3.8.0"
val circeVersion = "0.12.3"
dependencies {
    implementation("org.scala-lang:scala-library:2.12.10")
    implementation("io.vertx:vertx-lang-scala_2.12:$vertxVersion")
    implementation("io.vertx:vertx-web-scala_2.12:$vertxVersion")
    implementation("org.redisson:redisson:3.11.6")
    implementation("io.circe:circe-core_2.12:$circeVersion")
    implementation("io.circe:circe-parser_2.12:$circeVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileScala
}