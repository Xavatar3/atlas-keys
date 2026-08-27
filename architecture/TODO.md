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
