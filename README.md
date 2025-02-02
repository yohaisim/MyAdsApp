# 📢 MyAdsApp - Android Ad Display App  

[![Ads Library](https://your-image-link-here.jpg)](https://your-github-repo-link)

## 🎯 About This Project  
**MyAdsApp** is a simple Android application that fetches and displays advertisements using the custom library `AdsLibrary`, which was built and published via **JitPack**.  
The app rotates multiple ads every **10 seconds**, supports click tracking, and provides a smooth fade-in/fade-out transition.

## 🏗️ Built With
- **Android Studio** (Java)
- **Retrofit** (for API requests)
- **Picasso** (for image loading)
- **JitPack** (for dependency management)

## 📦 Features  
✅ Fetches ads dynamically from the API  
✅ Displays ads in a **centered wide rectangle**  
✅ **Auto-rotating ads** with a **10-second timer**  
✅ Smooth **fade-in / fade-out** transition effects  
✅ **Click tracking & view reporting** via API  
✅ Fully **integrated with AdsLibrary** via JitPack  

## 📸 App Preview  
![App Preview](https://your-image-link-here.jpg)  

---

## 🔧 Installation  

### 1️⃣ Add JitPack to `settings.gradle`
Ensure your project includes JitPack as a repository:
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
