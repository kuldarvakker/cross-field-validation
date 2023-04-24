import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.asciidoctor.convert") version "2.4.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "ee.taltech"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

	//hibernate
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

	// valiktor
	implementation("org.valiktor:valiktor-core:0.12.0")

	// java fluent
	implementation("com.github.mvallim:java-fluent-validator:1.10.0")

	// konform
	implementation("io.konform:konform-jvm:0.4.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	project.property("snippetsDir")!!.let { outputs.dir(it) }
}

tasks.asciidoctor {
	project.property("snippetsDir")!!.let { inputs.dir(it) }
	dependsOn(tasks.test)
}
