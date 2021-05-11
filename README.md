# Scratch Kotlin Project
Scratch Project for the Kotlin Test-Teach for DevelopIntelligence.

# Getting Started
 * Start by cloning this repo
 * Next open the project in Android Studio 4.2 or newer
 * Navigate to **app** > java > com.example.scratches (test)
 * Open [`ScratchTests.kt`](./app/src/test/java/com/example/scratches/ScratchTests.kt)
 
## This project: 
  * Fixes the issue where creating a new project in Android Studio 4.2 tries initializing the Kotlin Gradle plugin with version `1.5.0-release-764` when that is not available (project is set to use version `1.5.0`.)
  * `kotlinx-serialization` already added to the classpath and is available to the UnitTest runner.
  * [`ScratchTests.kt`](./app/src/test/java/com/example/scratches/ScratchTests.kt) which has a number of tests in a red/green state (will be addressed during the teach).
  
