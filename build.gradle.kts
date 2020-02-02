buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.android.gradlePlugin)
        classpath(Libs.kotlin.gradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
