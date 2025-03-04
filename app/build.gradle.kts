plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    id(libs.plugins.navigation.safeargs.get().pluginId)
//    apply plugin: "com.google.gms.google-services"
//    apply plugin: "com.google.firebase.crashlytics"
}

android {
            namespace = Configuration.applicationId
    ndkVersion = Configuration.ndkVersion
            compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = Configuration.applicationId
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled = true
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
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        abortOnError = false
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":lib_startup"))
    implementation(project(":base_presentation"))
    implementation(project(":presentation_detail"))
    implementation(project(":presentation_list"))
    implementation(project(":presentation_login"))
    implementation(project(":presentation_splash"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)

    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    implementation(libs.androidx.lifecycle)

    implementation(libs.hilt)
    kapt(libs.hilt.kapt)

    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp)

    implementation(libs.room)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    implementation(libs.glide)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)

    debugImplementation debugBuildDependencies.flipper
    debugImplementation debugBuildDependencies.flipperNetwork
    debugImplementation debugBuildDependencies.soloader

    debugImplementation debugBuildDependencies.leakcanary

    // MockK
    testImplementation "io.mockk:mockk:$mockk_version"

    // Hamkrest
    testImplementation "com.natpryce:hamkrest:$hamkrest_version"

    testImplementation "junit:junit:$junit_version"
    androidTestImplementation "androidx.test.ext:junit:$androidx_junit_version"
    androidTestImplementation "androidx.test.espresso:espresso-core:$espresso_version"
}
