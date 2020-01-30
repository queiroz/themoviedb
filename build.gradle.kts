buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.android.gradlePlugin)
        classpath(Deps.kotlin.gradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
