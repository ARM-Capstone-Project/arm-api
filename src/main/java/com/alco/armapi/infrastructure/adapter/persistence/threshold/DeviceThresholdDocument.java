package com.alco.armapi.infrastructure.adapter.persistence.threshold;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@DynamoDBTable(tableName = "device_threshold") // This must be the same as in DynamoDB
@DynamoDBDocument
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceThresholdDocument {
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private String deviceId;

    @DynamoDBAttribute
    private String sensorId;

    //temperature or humidity or methane
    @DynamoDBAttribute
    private String reading;

    @DynamoDBAttribute
    private String condition;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String level;

    @DynamoDBAttribute
    private String unit;
}
