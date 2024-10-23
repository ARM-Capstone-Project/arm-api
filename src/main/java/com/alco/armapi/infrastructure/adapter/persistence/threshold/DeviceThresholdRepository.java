package com.alco.armapi.infrastructure.adapter.persistence.threshold;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceThresholdRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(DeviceThresholdDocument deviceThreshold) {
        if(ObjectUtils.isEmpty(deviceThreshold.getId())){
            deviceThreshold.setId(null);
        }
        dynamoDBMapper.save(deviceThreshold);
    }
    public List<DeviceThresholdDocument> findAll() {
        // Scan the DynamoDB table to get all DeviceThreshold items
        return dynamoDBMapper.scan(DeviceThresholdDocument.class, new DynamoDBScanExpression());
    }

    public void delete(DeviceThresholdDocument deviceThreshold) {
        dynamoDBMapper.delete(deviceThreshold);
    }

    public void deleteAll() {
        List<DeviceThresholdDocument> thresholds = findAll();
        for (DeviceThresholdDocument threshold : thresholds) {
            delete(threshold);
        }
    }

    public List<DeviceThresholdDocument> findByDeviceId(String deviceId) {
        DeviceThresholdDocument deviceThreshold = new DeviceThresholdDocument();
        deviceThreshold.setDeviceId(deviceId);

        DynamoDBQueryExpression<DeviceThresholdDocument> queryExpression = new DynamoDBQueryExpression<DeviceThresholdDocument>()
                .withHashKeyValues(deviceThreshold);

        return dynamoDBMapper.query(DeviceThresholdDocument.class, queryExpression);
    }

}
