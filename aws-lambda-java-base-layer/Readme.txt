Run mvn_deploy_local_jars.sh to set up local-maven-repo from jarfiles
Then run mvn_build.sh to download maven dependencies and local repo dependencies
Finally, run package_layer.sh to create the .zip file and upload it to AWS as a layer

Go to AWS Lamba function CLI interface on web site and add the layer as a "custom" 
layer to the lambda function.  If you update the layer, be sure to edit the layer in 
the lambda function to increment the version number (the version is automatically
incremented each time you update the layer)