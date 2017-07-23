# YCode Scanner

Create custom Scan codes,based on image, and use it as Bar/QR Code.

# Features
  - Use any image.
  - Android application
  - Cordova Android PLugin

# Generate YCode :
[Generator][ycode]

### Server

* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework 

### Client 
* [Java]
* [Opencv]

And of course YCode itself is open source with a [public repository][github]
 on GitHub.

### Usage Cordova/Ionic [dependencies]

1) Create Maven local repository:
```sh
mvn install:install-file -Dfile=ycodelib-release.aar -DgroupId=com.ycode -DartifactId=ycode -Dversion=1.0.4 -Dpackaging=aar

mvn install:install-file -Dfile=openCVLibrary310-release.aar -DgroupId=com.opencv -DartifactId=opencv -Dversion=3.1.0 -Dpackaging=aar

```
2) Create Cordova/Ionic project:
```sh
cordova create ytestapp com.ycode.testapp YCodeScanner
cd ytestapp
cordova platform add android
```
3) Install Cordova plugin :
```sh
cordova plugin add ycode-cordova-plugin@0.0.1
```
4)Modify AndroidManifest.xml(platforms\android\AndroidManifest.xml):
* add  <application android:hardwareAccelerated="true" ...android:theme="@style/AppTheme">
* add activity:
        <activity android:name="caragulak.m8s.ycodelib.YCodeActivity">
            <intent-filter>
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
* add <uses-sdk android:minSdkVersion="21" android:targetSdkVersion="25" />

5) copy files from "platforms\android\res\values":
    * colors.xml
    * styles.xml
6) Edit www/js/index.js  to use Ycode JS code:
```sh
ycode.openScanner(
		apiKey, 
		function(msg) { 
		    // msg = scanned text from ycode image
		},
		function(err) {
		  // err = error message
		}
	  );
```
    > API KEY 1 : 3794f233c27c237571f14438cff11b7b
    > API KEY 2 : 510714b991e3f5479d11f3ff86f5f8e1




License
----

MIT


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [ycode]: <http://m8s.nsupdate.info/lucky/ycode_v2.1.html>
   [github]:<http://m8s.nsupdate.info/lucky/ycode_v2.1.html>
