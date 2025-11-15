plugins {
    id("com.android.library")
    id("kotlin-android")
}

// ✅ VERSION CONFIGURATION - DYNAMIC & UPDATED
val compileSdkVersion by extra(34)
val minSdkVersion by extra(21)
val targetSdkVersion by extra(34)
val kotlinVersion by extra("1.9.22")

// ✅ LIBRARY VERSIONS - UPDATED & SECURE
val playServicesLocationVersion by extra("21.0.1")
val gsonVersion by extra("2.10.1")
val materialVersion by extra("1.10.0")

group = "yukams.app.background_locator_2"
version = "1.0.0"

android {
    namespace = "yukams.app.background_locator_2"
    compileSdk = compileSdkVersion

    sourceSets {
        main.get().java.srcDirs("src/main/kotlin")
    }

    defaultConfig {
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        disable.add("InvalidPackage")
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("com.google.android.gms:play-services-location:$playServicesLocationVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.google.android.material:material:$materialVersion")
    
    // ✅ ANDROIDX DEPENDENCIES FOR MODERN COMPATIBILITY
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    
    // ✅ TEST DEPENDENCIES (OPTIONAL)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

// ✅ TASKS FOR CODE QUALITY
tasks {
    withType<Test> {
        useJUnit()
    }
    
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}