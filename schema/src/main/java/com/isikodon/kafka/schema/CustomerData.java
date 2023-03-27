package com.isikodon.kafka.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerData {
    private String customerId;
    private Instant timestamp;
    private List<String> products;
    private String ipAddress;
    private int responseTime;
    private String websiteUrl;


    // this would usually be placed in a utility class, but for this exercise we are placing it here
    public CustomerData (String params){
        String[] paramsArray = params.split(",");
        customerId = paramsArray[0];
        timestamp = Instant.parse(paramsArray[1]);
        products = Arrays.asList(paramsArray[2].split("\\$"));
        ipAddress = paramsArray[3];
        responseTime = Integer.parseInt(paramsArray[4]);
        websiteUrl = paramsArray[5];
    }
}
