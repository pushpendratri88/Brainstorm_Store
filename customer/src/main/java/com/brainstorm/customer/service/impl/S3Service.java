package com.brainstorm.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    public String uploadFile(String key, byte[] file) {
         s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(), RequestBody.fromBytes(file));
         return getUrl(key);
    }

    private String getUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key);
    }
}
