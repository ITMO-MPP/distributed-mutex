
group = "ru.ifmo.pds"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    testImplementation(kotlin("test-junit"))
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
    }
    test {
        java.setSrcDirs(listOf("test"))
    }
}

val processId = project.properties["processId"] as? String ?: "1"
val implName = project.properties["implName"] as? String ?: "ProcessImpl"

tasks {
    test {
        testLogging.showStandardStreams = true
        systemProperty("implName", implName)
        project.properties["skipCountCheck"]?.let { systemProperty("skipCountCheck", it) }
    }

    register<JavaExec>("node") {
        classpath = sourceSets["main"].runtimeClasspath
        main = "mutex.system.NodeKt"
        args = listOf(processId, implName)
        standardInput = System.`in`
    }

    register<JavaExec>("system") {
        classpath = sourceSets["main"].runtimeClasspath
        main = "mutex.system.SystemKt"
        args = listOf(implName)
        standardInput = System.`in`
    }
}
