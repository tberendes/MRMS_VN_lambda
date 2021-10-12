#!/bin/bash
mkdir -p java/lib
cp target/aws-lambda-java-base-layer-1.jar java/lib
zip -r aws-lambda-java-base-layer.zip java/lib
aws s3 cp aws-lambda-java-base-layer.zip s3://capri-dev/aws-lambda-java-base-layer.zip 
aws lambda publish-layer-version --layer-name aws-lambda-java-base-layer --description "AWS Lambda Java base dependency layer" --content S3Bucket=capri-dev,S3Key=aws-lambda-java-base-layer.zip  --compatible-runtimes java8.al2 
