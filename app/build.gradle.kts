import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("org.jetbrains.dokka").version("0.10.0")
}

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
            buildConfigField("String", "URI", "\"https://api.themoviedb.org/3\"")
            isDebuggable = true
        }
        getByName("release") {
            buildConfigField("String", "URI", "\"https://api.themoviedb.org/3\"")
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.sourceCompat
        targetCompatibility = Versions.targetCompat
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
    implementation(Deps.kotlin.stdlib)
    implementation(Deps.kotlin.coroutines)
    // Android
    implementation(Deps.android.design)
    implementation(Deps.android.ktxCore)
    // Support
    implementation(Deps.support.appCompat)
    implementation(Deps.support.recyclerView)
    implementation(Deps.support.constraintLayout)
    // JetPack
    implementation(Deps.jetpack.lifecycleExtensions)
    implementation(Deps.jetpack.navigationFragment)
    implementation(Deps.jetpack.navigationUi)
    implementation(Deps.jetpack.roomRuntime)
    implementation(Deps.jetpack.pagingRuntime)
    // compilers
    kapt(Deps.jetpack.lifecycleCompiler)
    kapt(Deps.jetpack.roomCompiler)
    kapt(Deps.google.daggerCompiler)
    // Retrofit
    implementation(Deps.other.retrofit)
    implementation(Deps.other.retrofitGson)
    // Other
    implementation(Deps.other.timber)
    implementation(Deps.other.threeTenBp)

    // Tests
    testImplementation(Deps.test.junit)
    testImplementation(Deps.test.mockito)
    testImplementation(Deps.test.robolectric)
    testImplementation(Deps.test.mockWebServer)
    testImplementation(Deps.test.livedataTesting)
    testImplementation(Deps.test.roomTesting)

    androidTestImplementation(Deps.test.junit)
    androidTestImplementation(Deps.test.atslRunner)
    androidTestImplementation(Deps.test.atslRules)
    androidTestImplementation(Deps.test.roomTesting)
    androidTestImplementation(Deps.test.livedataTesting)
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
