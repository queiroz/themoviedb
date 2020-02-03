object Config {
    const val applicationId = "org.queiroz.themoviedb"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionName = "1.0"
    const val versionCode = 1
}

object Gradle {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val dokkaPlugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Versions.kotlinDokka}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"
}

object Android {
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
    const val lifecycleViewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val transition = "androidx.transition:transition:${Versions.transition}"
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
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutine}"
    const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Google {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
}

object Other {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val plainPie = "com.github.zurche:plain-pie:${Versions.plainPie}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Maven {
    const val google = "https://maven.google.com/"
    const val fabricRepository = "https://maven.fabric.io/public"
}

object Libs {
    val kotlin = Kotlin
    val android = Android
    val support = Support
    val jetpack = Jetpack
    val google = Google
    val maven = Maven
    val gradle = Gradle
    val other = Other
    val test = Test
}
