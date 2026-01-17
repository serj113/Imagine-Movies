plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    namespace = "com.serj113.imaginemovies.base.domain"
    compileSdk = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    lint {
        abortOnError = false
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":base_model"))

    implementation(libs.kotlin.stdlib)

    implementation(libs.kotlin.coroutines.core)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

//            testImplementation "junit:junit:$junit_version"
//    androidTestImplementation "androidx.test.ext:junit:$androidx_junit_version"
}
