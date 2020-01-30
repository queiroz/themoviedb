object Config {
    const val applicationId = "org.queiroz.themoviedb"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionName = "1.0"
    const val versionCode = 1
}


object Kotlin {
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"
    const val dokkaPlugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Versions.kotlinDokka}"
}

object Android {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val design = "com.google.android.material:material:${Versions.material}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.androidKtxCore}"
}

object Support {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    const val recyclerView = "com.android.support:recyclerview-v7:${Versions.supportLibrary}"
    const val cardView = "com.android.support:cardview-v7:${Versions.supportLibrary}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Jetpack {
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val atslRunner = "com.android.support.test:runner:${Versions.atsl}"
    const val atslRules = "com.android.support.test:rules:${Versions.atsl}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val roomTesting = "android.arch.persistence.room:testing:${Versions.room}"
    const val livedataTesting = "android.arch.core:core-testing:${Versions.lifecycle}"
}

object Google {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
}

object Other {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    // Utils
    // Date and time API for Java.
    const val threeTenBp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenaBp}"
}

object Maven {
    const val google = "https://maven.google.com/"
    const val fabricRepository = "https://maven.fabric.io/public"
}

object Deps {
    val kotlin = Kotlin
    val android = Android
    val support = Support
    val jetpack = Jetpack
    val google = Google
    val maven = Maven
    val other = Other
    val test = Test
}
