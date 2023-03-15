import io.getstream.video.android.Configuration
import io.getstream.video.android.Dependencies
import io.getstream.video.android.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("binary-compatibility-validator")
}

rootProject.extra.apply {
    set("PUBLISH_GROUP_ID", Configuration.artifactGroup)
    set("PUBLISH_ARTIFACT_ID", "stream-video-android-xml")
    set("PUBLISH_VERSION", rootProject.extra.get("rootVersionName"))
}

apply(from ="${rootDir}/scripts/publish-module.gradle")

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    lint {
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    api(project(":stream-video-android-core"))
    api(project(":stream-video-android-ui-common"))

    implementation(Dependencies.material)
    implementation(Dependencies.streamLog)
    implementation(Dependencies.coil)

    // AndroidX
    implementation(Dependencies.androidxActivity)
    implementation(Dependencies.androidxLifecycleRuntime)
    implementation(Dependencies.androidxStartup)
}