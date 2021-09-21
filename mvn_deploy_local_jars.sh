#!/bin/bash
mvn deploy:deploy-file -DgroupId='commons-math3' -DartifactId='commons-math3' -Dversion='3.6.1' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/commons-math3-3.6.1.jar'
mvn deploy:deploy-file -DgroupId='commons-math3-tools' -DartifactId='commons-math3-tools' -Dversion='3.6.1' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/commons-math3-3.6.1-tools.jar'
mvn deploy:deploy-file -DgroupId='jcommon' -DartifactId='jcommon' -Dversion='1.0.23' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/jcommon-1.0.23.jar'
mvn deploy:deploy-file -DgroupId='jfreechart-experimental' -DartifactId='jfreechart-experimental' -Dversion='1.0.19' -Durl='file:./local-maven-repo/'  -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/jfreechart-1.0.19-experimental.jar'
mvn deploy:deploy-file -DgroupId='jfreechart' -DartifactId='jfreechart' -Dversion='1.0.19' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/jfreechart-1.0.19.jar'
mvn deploy:deploy-file -DgroupId='junit' -DartifactId='junit' -Dversion='4.11' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/junit-4.11.jar'
mvn deploy:deploy-file -DgroupId='netcdf' -DartifactId='netcdf' -Dversion='4.6' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/netcdfAll-4.6.jar'
mvn deploy:deploy-file -DgroupId='slf4j-api' -DartifactId='slf4j-api' -Dversion='1.7.6' -Durl='file:./local-maven-repo/' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/slf4j-api-1.7.6.jar'
mvn deploy:deploy-file -DgroupId='slf4j-simple' -DartifactId='slf4j-simple' -Durl='file:./local-maven-repo/' -Dversion='1.7.7' -DrepositoryId='local-maven-repo' -DupdateReleaseInfo='true' -Dfile='./jarfiles/slf4j-simple-1.7.7.jar'

