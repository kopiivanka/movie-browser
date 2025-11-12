import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "org.kopytsia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.jetbrains.compose.material3:material3-desktop:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.7.0")
    implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.1")
    implementation("io.ktor:ktor-client-cio:3.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
    implementation("io.ktor:ktor-network-tls:3.0.0")
    implementation("io.ktor:ktor-network-tls-certificates:3.0.0")
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "movie-browser"
            packageVersion = "1.0.0"
        }
    }
}
