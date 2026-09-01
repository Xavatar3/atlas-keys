## Manifest
Path - *app/src/main/AndroidManifest.xml*

### Permissions
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_MEDIA_AUDIO"/>
<uses-permission android:name="android.permission.READ_MEDIA_VIDEO"/>
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

**internet:** Downloading/Uploading resources.  
**audio:** Importing local sound files.  
**images:** Importing local image files.  
**video:** Currently unneccessary.  
**read/write:** Importing/Exporting local resources.  

**Note:** *Optional, only requested when required.*  

### App 
- Localize for several languages
- Rename or move, app.kt, and re-allocate folders to other files

### App.kt
Path - *app/App.kt*
- Make Onboarding appear only initially and persist. not every time when switching key boards

### Theme.kt
Path - *app/theme.kt*
- Move themes to json

### Splash.kt
- Reimplentent it using androids Splash screeb api

### Repository.kt
Path - *engine/kengine/Repository.kt*
- Store Resolved layouts on disk and load later
- Add Resolution and prewarming

### CI/CD
Path - *.github/workflows/keyforge.yml*
- Split debug and release steps into parallel jobs.
- Keep alive for warm rebuilds for sometime or after failure
- get gradle stats like time and size usage
- Improve cache
- Set up and implement an emulator with tests
- Merge the build reports folder into its parent.

### Gradle
Path - *app/build.gradle.kts*
- Have both AAP and APK.
