#!/bin/bash
aws lambda update-function-code --function-name capri_MrmsGeoMatch  --zip-file fileb://target/capri_geomatch_mrms-1.0.jar
