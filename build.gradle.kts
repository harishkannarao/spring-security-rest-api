import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	java
	id("java-test-fixtures")
	id("org.springframework.boot") version "3.0.1"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.harishkannarao"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
	testFixturesImplementation("org.springframework.security:spring-security-test")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

testing {
	suites {
		val test by getting(JvmTestSuite::class) {

		}
		val integrationTest by registering(JvmTestSuite::class) {
			dependencies {
				implementation(project())
				implementation(testFixtures(project()))
			}
			targets {
				all {
					testTask.configure {
						mustRunAfter(test)
					}
				}
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

	testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)

	val properties = System.getProperties().entries.associate { it.key.toString() to it.value }
	systemProperties(properties)
}

tasks.named("check") {
	dependsOn(testing.suites.named("integrationTest"))
}
