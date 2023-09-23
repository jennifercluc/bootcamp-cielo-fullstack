package com.bootcamp.queuemanager.configuration;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.util.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfiguration {
    @Value("${aws.accessKeyId}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${cloud.aws.region}")
    private String awsRegion;

    private AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(awsAccessKey, awsSecretKey));
    }

    @Bean
    public SnsClient snsClient() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(awsAccessKey, awsSecretKey));

        return SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(awsCredentialsProvider())
                .build();
    }

    @Bean
    public SqsClient sqsClient() {

        return SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(awsCredentialsProvider())
                .build();
    }

    @Bean
    public ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> concurrentHashMap() {
        ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>> map = new ConcurrentHashMap<Type, LinkedList<CustomerFeedbackDTO>>();
        map.put(Type.ELOGIO, new LinkedList<>());
        map.put(Type.SUGESTAO, new LinkedList<>());
        map.put(Type.CRITICA, new LinkedList<>());

        return map;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addAllowedOrigin("http://localhost");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }

}
