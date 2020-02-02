import Config.applicationId
import Config.versionCode
import Config.versionName
import Libs.android
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("org.jetbrains.dokka").version("0.10.0")
}

val theMovieDbApiKey: String? by project

android {
    compileSdkVersion(Config.compileSdk)
    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "URI", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "THE_MOVIE_DB_API_KEY", theMovieDbApiKey)
            isDebuggable = true
        }
        getByName("release") {
            buildConfigField("String", "URI", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "THE_MOVIE_DB_API_KEY", theMovieDbApiKey)
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.sourceCompat
        targetCompatibility = Versions.targetCompat
    }
    buildFeatures{
        dataBinding = true
    }
    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
        abi { enableSplit = true }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // kotlin
    implementation(Libs.kotlin.stdlib)
    implementation(Libs.kotlin.coroutines)
    implementation(Libs.kotlin.coroutinesAndroid)
    // Android
    implementation(Libs.android.design)
    implementation(Libs.android.ktxCore)
    // Support
    implementation(Libs.support.appCompat)
    implementation(Libs.support.recyclerView)
    implementation(Libs.support.constraintLayout)
    // JetPack
    implementation(Libs.jetpack.lifecycleViewModelExtensions)
    implementation(Libs.jetpack.fragment)
    implementation(Libs.jetpack.navigationFragment)
    implementation(Libs.jetpack.navigationUi)
    implementation(Libs.jetpack.roomRuntime)
    implementation(Libs.jetpack.pagingRuntime)
    // compilers
    kapt(Libs.jetpack.lifecycleCompiler)
    kapt(Libs.jetpack.roomCompiler)
    kapt(Libs.google.daggerCompiler)
    kapt(Libs.google.daggerAndroidCompiler)
    // dagger
    implementation(Libs.google.dagger)
    implementation(Libs.google.daggerAndroid)
    implementation(Libs.google.daggerAndroidSupport)
    // Retrofit
    implementation(Libs.other.retrofit)
    implementation(Libs.other.retrofitGson)
    implementation(Libs.other.okHttpLoggingInterceptor)
    // Other
    implementation(Libs.other.timber)
    implementation(Libs.other.plainPie)
    implementation(Libs.other.picasso)

    // Tests
    testImplementation(Libs.test.junit)
    testImplementation(Libs.test.mockito)
    testImplementation(Libs.test.robolectric)
    testImplementation(Libs.test.mockWebServer)
    testImplementation(Libs.test.livedataTesting)
    testImplementation(Libs.test.roomTesting)
    testImplementation(Libs.test.coroutinesTest)
    testImplementation(Libs.test.fragmentTest)

    androidTestImplementation(Libs.test.junit)
    androidTestImplementation(Libs.test.atslRunner)
    androidTestImplementation(Libs.test.atslRules)
    androidTestImplementation(Libs.test.roomTesting)
    androidTestImplementation(Libs.test.livedataTesting)
}

val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
    configuration {
        // Use to include or exclude non public members.
        includeNonPublic = true
        // Emit warnings about not documented members. Applies globally, also can be overridden by packageOptions
        reportUndocumented = false
    }
}

defaultTasks("dokka")
