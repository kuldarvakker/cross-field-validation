import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.asciidoctor.convert") version "2.4.0"
	id("com.google.devtools.ksp") version "1.9.10-1.0.13"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.8.20"
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
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.2.0")
	implementation("org.springdoc:springdoc-openapi-starter-common:2.2.0")

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

	// yavi
	implementation("am.ik.yavi:yavi:0.12.1")

	// akkurate
	implementation("dev.nesk.akkurate:akkurate-core:0.4.0")
	implementation("dev.nesk.akkurate:akkurate-ksp-plugin:0.4.0")
	ksp("dev.nesk.akkurate:akkurate-ksp-plugin:0.4.0")

	// thing
	implementation("so.kciter:thing:0.0.8")

	// kalidation
	implementation("io.github.rcapraro:kalidation:1.9.1")
	implementation("io.arrow-kt:arrow-core:1.2.0-RC")
	implementation("jakarta.validation:jakarta.validation-api:3.0.1")
	implementation("org.glassfish:jakarta.el:4.0.2")

	// validoctor
	implementation("com.miquido:validoctor:2.1.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-Xemit-jvm-type-annotations")
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
