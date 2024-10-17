package com.alco.armapi.infrastructure.adapter.persistence.readings;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Repository
public class DeviceSensorReadingRepository {
    public static final String DEVICE_ID = ":deviceId";
    public static final String DEVICE_ID_DEVICE_ID = "deviceId = :deviceId";
    public static final String SENSOR_READINGS_SIZE = "sensorReadings size ";
    public static final String BATCH_SAVE_FAILED = "Batch save failed:";
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(DeviceSensorReadingDocument deviceSensorReading) {

        dynamoDBMapper.save(deviceSensorReading);
    }
    public List<DeviceSensorReadingDocument> findAll() {
        // Scan the DynamoDB table to get all SensorReading items
        return dynamoDBMapper.scan(DeviceSensorReadingDocument.class, new DynamoDBScanExpression());
    }

    public void delete(DeviceSensorReadingDocument deviceSensorReading) {
        dynamoDBMapper.delete(deviceSensorReading);
    }

    public void deleteAll() {
        List<DeviceSensorReadingDocument> thresholds = findAll();
        for (DeviceSensorReadingDocument threshold : thresholds) {
            delete(threshold);
        }
    }




    public List<DeviceSensorReadingDocument> findByDeviceId(String deviceId) {

        // Create a map for expression attribute values
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(DEVICE_ID, new AttributeValue().withS(deviceId));

        // Create a scan expression with a filter for deviceId
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(DEVICE_ID_DEVICE_ID)
                .withExpressionAttributeValues(eav);

        // Perform the scan


        return dynamoDBMapper.scan(DeviceSensorReadingDocument.class, scanExpression);
    }


    public void batchSave(List<DeviceSensorReadingDocument> deviceSensorReadings) {
        try {
            log.info(SENSOR_READINGS_SIZE + "{}", deviceSensorReadings.size());
            for (DeviceSensorReadingDocument deviceSensorReading : deviceSensorReadings) {
                dynamoDBMapper.save(deviceSensorReading);
            }
        } catch (Exception e) {

            log.error(BATCH_SAVE_FAILED + " {}", e.getMessage());
        }
    }


}
