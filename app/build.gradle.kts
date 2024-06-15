plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.educonnectadmin"
    compileSdk = 27

    defaultConfig {
        applicationId = "com.example.educonnectadmin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

        implementation(libs.appcompat.v120)
        implementation(libs.material.v121)
        implementation(libs.constraintlayout)
        implementation(libs.firebase.database.v2006)
        implementation(libs.firebase.storage.v2002)
        implementation(libs.firebase.messaging)
        testImplementation(libs.junit.v4131)
        androidTestImplementation(libs.junit.v112)
        androidTestImplementation(libs.espresso.core.v330)
        implementation(libs.material.v121)
        implementation(libs.firebase.bom)
        implementation(libs.firebase.analytics)
        implementation(libs.circleimageview)
        implementation(libs.picasso)
        implementation(libs.passcodeview)
        implementation(libs.legacy.support.v4)
        implementation(libs.shimmer)
        implementation(libs.retrofit)
        implementation(libs.converter.gson)
        implementation(libs.jitsi.meet.sdk)



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.storage)
    implementation(libs.play.services.tasks)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}