@ECHO OFF

mvn install:install-file -Dfile=openCVLibrary310-release.aar -DgroupId=com.opencv -DartifactId=opencv -Dversion=3.1.0 -Dpackaging=aar

PAUSE