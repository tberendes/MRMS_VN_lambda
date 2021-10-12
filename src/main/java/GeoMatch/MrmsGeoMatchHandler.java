package GeoMatch;

import java.io.IOException;
import java.io.File;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.event.S3EventNotification;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;

public class MrmsGeoMatchHandler implements RequestHandler<S3Event, String> {

	AmazonS3 s3;

    public MrmsGeoMatchHandler() 
    {
	    s3 = AmazonS3ClientBuilder.standard().build();
	        //.withCredentials(new ProfileCredentialsProvider())
	        //.build();
    }


    @Override
    public String handleRequest(S3Event event, Context ctx) {

	
	    S3EventNotification.S3EventNotificationRecord record=event.getRecords().get(0);
	     
	    String inputBucketName = record.getS3().getBucket().getName();
	    String keyName = record.getS3().getObject().getKey();
	    System.out.println("Bucket Name is "+inputBucketName);
	    System.out.println("File Path is "+keyName);
	     
		String mrmsPath = "mrms_mirror";
		String dataBucketName = "capri-data";
		String tmpOutDir = "outdir";
		String mrmsGeoMatchPath = "vn_mrms_geomatch";
		String tmpDir = File.separator+"tmp";

		File fp = new File(keyName);
	        
	    String filename = fp.getName();
	    String basename = fp.getParent(); // includes trailing slash
	    
		String vnInputFilename = tmpDir+File.separator+filename;

	    // download object to /tmp and set outDir for S3 prefix
		s3.getObject(
            new GetObjectRequest(inputBucketName, keyName),
            new File(vnInputFilename)
		);
		
		String outpath = tmpDir+File.separator+tmpOutDir;
 		File outDir = new File (outpath);
 		if (outDir.exists()&& outDir.isDirectory()) {
 			System.out.println("Output directory " + outpath + " exists");
		}
 		else if (outDir.isFile()){
 			System.out.println("Error:  Output directory path " + outpath + " is a file not a directory");
 			System.exit(-1);
 		}
 		else {
 			System.out.println("Creating output directory " + outpath);		
 			outDir.mkdirs();
 		}
 		if (!outDir.exists()) {
 			System.out.println("Error: could not create output directory " + outpath);
			System.exit(-1);
 		}

		// set output filename under temp directory
		String vnOutputFilename = outpath + File.separator + filename;
		
		MrmsGeoMatch mrmsGeo = new MrmsGeoMatch(mrmsPath,tmpDir, s3, dataBucketName);
		
		String outFilename = mrmsGeo.processFile(vnInputFilename, vnOutputFilename);
		
		if (outFilename != null) {
		
			// upload vnOutputFilename to output bucket location
			s3.putObject(
	            new PutObjectRequest(dataBucketName, mrmsGeoMatchPath+"/"+filename, new File(vnOutputFilename))
			);
			// also need to copy the .fp files to trigger creation of deep learning imagery
			
		    return mrmsGeoMatchPath+"/"+filename;
		}
		else {
		    return "No matching MRMS data for file "+inputBucketName+":"+keyName;
			
		}
    }
    
    
    
}