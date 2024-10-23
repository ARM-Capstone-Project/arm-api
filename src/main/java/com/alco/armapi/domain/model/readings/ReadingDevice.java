package com.alco.armapi.domain.model.readings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingDevice {

    private String id;

    private String deviceId;

    private Date timestamp;

    private List<ReadingSensor> readings;
}
