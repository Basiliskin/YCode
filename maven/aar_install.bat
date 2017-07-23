@ECHO OFF

mvn install:install-file -Dfile=ycodelib-release.aar -DgroupId=com.ycode -DartifactId=ycode -Dversion=1.0.4 -Dpackaging=aar

PAUSE