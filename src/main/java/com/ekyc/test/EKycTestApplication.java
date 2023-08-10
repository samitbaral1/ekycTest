package com.ekyc.test;

import com.ekyc.model.EkycRequestDto;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EKycTestApplication {
    @Value("${client_code}")
    private static String client_code;
    @Value("${route_key}")
    private static String route_key;


    public static void main(String[] args) {
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", client_code));
        formData.add(new BasicNameValuePair("route_key", route_key));
        formData.add(new BasicNameValuePair("api_num", String.valueOf(3)));
        formData.add(new BasicNameValuePair("image_num", String.valueOf(3)));

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost("https://dev-ap-dtrust.double-std.com/service/api/auth");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
