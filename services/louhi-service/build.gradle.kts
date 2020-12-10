import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "fi.hel.haitaton"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val camelVersion = "3.6.0"
val hawtIoVersion = "2.12.0"
val postgreSQLVersion = "42.2.18"
val springDocVersion = "1.4.8"
val geoJsonJacksonVersion = "1.14"
val mockkVersion = "1.10.2"
val springmockkVersion = "2.0.3"
val assertkVersion = "0.23"

repositories {
	mavenCentral()
}

sourceSets {
	create("integrationTest") {
		compileClasspath += main.get().output + test.get().output
		runtimeClasspath += main.get().output + test.get().output
	}
}

val integrationTestImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.testImplementation.get())
}

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.testRuntimeOnly.get())

idea {
	module {
		testSourceDirs =
				testSourceDirs + sourceSets["integrationTest"].withConvention(KotlinSourceSet::class) { kotlin.srcDirs }
		testResourceDirs = testResourceDirs + sourceSets["integrationTest"].resources.srcDirs
	}
}

springBoot {
	buildInfo()
}

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.21"
	// Gives kotlin-allopen, which auto-opens classes with certain annotations
	kotlin("plugin.spring") version "1.4.21"
	// Gives kotlin-noarg for @Entity, @Embeddable
	kotlin("plugin.jpa") version "1.4.21"
	idea
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
	}
	implementation("org.springframework.boot:spring-boot-starter-undertow") {
		exclude(group = "io.undertow", module = "undertow-websockets-jsr")
	}
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.apache.camel.springboot:camel-spring-boot-starter:$camelVersion")
	implementation("org.apache.camel.springboot:camel-stream-starter:$camelVersion")
	implementation("org.apache.camel.springboot:camel-quartz-starter:$camelVersion")
	implementation("org.apache.camel:camel-management:$camelVersion")
	implementation("io.hawt:hawtio-springboot:$hawtIoVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.github.microutils:kotlin-logging:1.12.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("de.grundid.opendatalab:geojson-jackson:$geoJsonJacksonVersion")
	implementation("org.liquibase:liquibase-core")
	runtimeOnly("org.postgresql:postgresql:$postgreSQLVersion")
	runtimeOnly("org.springdoc:springdoc-openapi-ui:$springDocVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.mockk:mockk:$mockkVersion")
	testImplementation("com.ninja-squad:springmockk:$springmockkVersion")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
	testImplementation("org.testcontainers:junit-jupiter:1.15.0")
	testImplementation("org.testcontainers:postgresql:1.15.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks {
	test {
		useJUnitPlatform()
		systemProperty("spring.profiles.active", "test")
	}

	create("integrationTest", Test::class) {
		useJUnitPlatform()
		group = "verification"
		systemProperty("spring.profiles.active", "integrationTest")
		testClassesDirs = sourceSets["integrationTest"].output.classesDirs
		classpath = sourceSets["integrationTest"].runtimeClasspath
		shouldRunAfter("test")
		outputs.upToDateWhen { false }
	}
}
