# YCode Scanner

Create custom Scan codes,based on image, and use it as Bar/QR Code.

# Features
  - Use any image.
  - Android application
  - Cordova Android PLugin
   
## Suporting Devices
    * arm64-v8a
    * x86_64
    
# Generate YCode 
[Generator][ycode]

### Server

* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework 

### Client 
* [Java]
* [Opencv]

And of course YCode itself is open source with a [public repository][github]
 on GitHub.

### Usage Android Studio
1) Create Maven local repository:
```sh
mvn install:install-file -Dfile=ycodelib-release.aar -DgroupId=com.ycode -DartifactId=ycode -Dversion=1.0.4 -Dpackaging=aar

mvn install:install-file -Dfile=openCVLibrary310-release.aar -DgroupId=com.opencv -DartifactId=opencv -Dversion=3.1.0 -Dpackaging=aar

```
2) Create Android Project
3) Modify build.gradle:
```sh
defaultConfig {
        minSdkVersion 21
        targetSdkVersion 25
        ...
}
repositories {
    mavenCentral()
    mavenLocal()
}
dependencies {
    ...
    compile('com.opencv:opencv:3.1.0@aar') {
        transitive = true
    }
    compile('com.ycode:ycode:1.0.4@aar') {
        transitive = true
    }
}
```
4) Add java code to handle YCode scanner:
```sh
import caragulak.m8s.ycodelib.YCodeActivity;

public class MainActivity extends AppCompatActivity {
    public final int MY_OP = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, YCodeActivity.class);
        intent.putExtra("apiKey", "3794f233c27c237571f14438cff11b7b");

        startActivityForResult(intent, MY_OP);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_OP) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("text");
                /*
                Snackbar.make(view, "Replace with your own action:"+result, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
```
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
