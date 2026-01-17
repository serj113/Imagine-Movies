plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    id(libs.plugins.navigation.safeargs.get().pluginId)
}

android {
    namespace = Configuration.applicationId
    compileSdk = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools
    ndkVersion = Configuration.ndkVersion

    defaultConfig {
        multiDexEnabled = true
        applicationId = Configuration.applicationId
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = Configuration.debugIdSuffix
            versionNameSuffix = Configuration.verDebugSuffix
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        val variantName = name
        kotlin.sourceSets {
            getByName(variantName) {
                kotlin.srcDir("${layout.buildDirectory.get()}/generated/source/navigation-args/$variantName")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":base_data"))
    implementation(project(":base_domain"))
    implementation(project(":base_model"))
    implementation(project(":base_presentation"))
    implementation(project(":common_navigation"))
    implementation(project(":common_presentation"))
    implementation(project(":feature_detail"))
    implementation(project(":feature_list"))
    implementation(project(":feature_login"))
    implementation(project(":feature_splash"))
    implementation(project(":lib_startup"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.legacy)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.multidex)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.glide)
    ksp(libs.glide.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)

    debugImplementation(libs.leakcanary)

    // MockK
//    testimplementation("io.mockk:mockk:$mockk_version")
//
//    // Hamkrest
//    testimplementation("com.natpryce:hamkrest:$hamkrest_version")
//
//    testimplementation("junit:junit:$junit_version")
//    androidTestimplementation("androidx.test.ext:junit:$androidx_junit_version")
//    androidTestimplementation("androidx.test.espresso:espresso-core:$espresso_version")
}

tasks.configureEach {
    if (name.startsWith("ksp") && name.endsWith("Kotlin")) {
        val variant = name.removePrefix("ksp").removeSuffix("Kotlin")
        dependsOn("generateSafeArgs${variant}")
    }
}
