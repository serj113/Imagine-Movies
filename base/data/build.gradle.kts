import java.io.FileInputStream
import java.util.Properties

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

val keystorePropertiesFile = rootProject.file("local.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.serj113.imaginemovies.base.data"
    compileSdk = Configuration.compileSdk
    buildToolsVersion = Configuration.buildTools

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

        buildConfigField("String", "API_KEY", "\"${keystoreProperties["api_key"]}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
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

//fun getProperty(filename: String, propName: String): String? {
//    val propsFile = rootProject.file(filename)
//    if (propsFile.exists()) {
//        val props = java.util.Properties()
//        props.load(java.io.FileInputStream(propsFile))
//        return props.getProperty(propName) ?: run {
//            print("No such property $propName in file $filename")
//            null
//        }
//    } else {
//        print("$filename does not exist!")
//        return null
//    }
//}

dependencies {
    implementation(project(":base_domain"))
    implementation(project(":base_model"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.legacy)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.multidex)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    implementation(libs.kotlin.coroutines.core)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

//            testImplementation 'junit:junit:4.12'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}
