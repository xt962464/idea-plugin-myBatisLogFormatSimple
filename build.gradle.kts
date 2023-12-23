plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij") version "1.16.1"
}

group = "com.alan.plugins"
version = "1.1.0"

repositories {
    maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public") }
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/spring") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.3.1") // 编译的 IDEA 版本
    type.set("IC") // Target IDE Platform

//    plugins.set(listOf(/* Plugin Dependencies */))
}

//将依赖打进jar包中
tasks.jar.configure {
    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.INCLUDE
    from(configurations.runtimeClasspath.get().filter { it.name.endsWith("jar")}.map { zipTree(it) })
}

tasks {
    // JVM 兼容性版本, 为 Java 17
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        options.encoding = "UTF-8"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    // 修改插件兼容的IDE版本范围为2023.1到2024..1
    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    // 插件签名(可忽略)
    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    // 自动发布插件任务(可忽略)
    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

dependencies {
    // https://mvnrepository.com/artifact/com.github.vertical-blank/sql-formatter
    implementation("com.github.vertical-blank:sql-formatter:2.0.4")

}