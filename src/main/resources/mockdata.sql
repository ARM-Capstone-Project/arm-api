-- Insert mock zone data for Singapore
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO zones (id, name, latitude, longitude, radius)
VALUES
    (gen_random_uuid(), 'Marina Bay', 1.283333, 103.860833, 2),
    (gen_random_uuid(), 'Orchard Road', 1.305801, 103.831736, 3),
    (gen_random_uuid(), 'Sentosa Island', 1.249404, 103.830321, 3),
    (gen_random_uuid(), 'Changi Airport', 1.364420, 103.991531, 3),
    (gen_random_uuid(), 'Singapore Zoo', 1.404348, 103.793023, 3),
    (gen_random_uuid(), 'Jurong East', 1.332600, 103.743600, 3),
    (gen_random_uuid(), 'Bukit Timah', 1.348290, 103.776399, 2),
    (gen_random_uuid(), 'Little India', 1.306996, 103.849915, 3),
    (gen_random_uuid(), 'East Coast Park', 1.294900, 103.912116, 2),
    (gen_random_uuid(), 'Punggol', 1.398396, 103.907814, 3);

select * from zones;

INSERT INTO devices (id, name, batch_no, description)
VALUES
    (gen_random_uuid(), 'Raspberry Pi 4', 'RP4-B001', 'Raspberry Pi 4 Model B with 4GB RAM, 64-bit quad-core processor'),
    (gen_random_uuid(), 'Raspberry Pi 5', 'RP5-B001', 'Raspberry Pi 5 with 8GB RAM, dual 4K video output, and PCIe expansion'),
    (gen_random_uuid(), 'Arduino Uno', 'ARDU-001', 'Arduino Uno Rev3 microcontroller board based on the ATmega328P'),
    (gen_random_uuid(), 'ESP32', 'ESP32-DEVKIT', 'ESP32 DevKit with Wi-Fi, Bluetooth LE, and dual-core processor'),
    (gen_random_uuid(), 'Jetson Nano', 'JETSON-NANO-B001', 'NVIDIA Jetson Nano Developer Kit with 4GB memory for AI projects'),
    (gen_random_uuid(), 'BeagleBone Black', 'BBB-REV-C', 'BeagleBone Black Rev C, AM335x 1GHz ARM Cortex-A8 processor'),
    (gen_random_uuid(), 'ESP8266', 'ESP8266MOD', 'ESP8266 Wi-Fi module with 80 MHz CPU and 2MB flash memory'),
    (gen_random_uuid(), 'Intel Edison', 'EDISON-B001', 'Intel Edison Compute Module with dual-core Intel Atom processor'),
    (gen_random_uuid(), 'Odroid XU4', 'ODROID-XU4', 'ODROID XU4 with ARM Cortex-A15 quad-core and Cortex-A7 quad-core CPUs'),
    (gen_random_uuid(), 'Tinker Board', 'TINKERBOARD-S', 'ASUS Tinker Board S with Rockchip RK3288 SoC, 2GB RAM, and HDMI output');

select * from devices;

--added for devices after zone insertion
INSERT INTO devices (id, name, batch_no, description, type, location, latitude, longitude, status, zone_id)
VALUES
  (gen_random_uuid(), 'Device 1', 'Batch123', 'Gas Detector', 'gas_detector', 'Building A', 1.3521, 103.8198, 'active', 
    '078d8ff6-cc2f-4db8-96a7-9ba34bbe96ef'),
  (gen_random_uuid(), 'Device 2', 'Batch124', 'Temperature Sensor', 'temperature', 'Building B', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 2)),
  (gen_random_uuid(), 'Device 3', 'Batch125', 'Humidity Sensor', 'humidity', 'Building C', 1.3521, 103.8198, 'inactive', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 3)),
  (gen_random_uuid(), 'Device 4', 'Batch126', 'Smoke Detector', 'smoke', 'Building D', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 4)),
  (gen_random_uuid(), 'Device 5', 'Batch127', 'Pressure Sensor', 'pressure', 'Building E', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 5)),
  (gen_random_uuid(), 'Device 6', 'Batch128', 'CO2 Sensor', 'CO2', 'Building F', 1.3521, 103.8198, 'inactive', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 6)),
  (gen_random_uuid(), 'Device 7', 'Batch129', 'Water Leak Detector', 'water_leak', 'Building G', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 7)),
  (gen_random_uuid(), 'Device 8', 'Batch130', 'Vibration Sensor', 'vibration', 'Building H', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 8)),
  (gen_random_uuid(), 'Device 9', 'Batch131', 'Noise Sensor', 'noise', 'Building I', 1.3521, 103.8198, 'inactive', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 9)),
  (gen_random_uuid(), 'Device 10', 'Batch132', 'Light Sensor', 'light', 'Building J', 1.3521, 103.8198, 'active', 
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 10));

update devices as x set latitude = (select latitude from zones as y where x.zone_id = y.id);
update devices as x set longitude = (select latitude from zones as y where x.zone_id = y.id);
commit;
select * from devices;

--sensors
INSERT INTO sensors (id, name, type, unit, status, device_id)
VALUES
  (gen_random_uuid(), 'Temperature Sensor A', 'temperature', '°C', 'active', (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), 'Temperature Sensor B', 'temperature', '°C', 'inactive', (SELECT id FROM devices WHERE name = 'Device 2')),
  (gen_random_uuid(), 'Humidity Sensor A', 'humidity', '%', 'active', (SELECT id FROM devices WHERE name = 'Device 2')),
  (gen_random_uuid(), 'Gas Sensor A', 'gas', 'ppm', 'active', (SELECT id FROM devices WHERE name = 'Device 1')),
  (gen_random_uuid(), 'CO2 Sensor A', 'CO2', 'ppm', 'inactive', (SELECT id FROM devices WHERE name = 'Device 6')),
  (gen_random_uuid(), 'Smoke Detector A', 'smoke', 'ppm', 'active', (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), 'Water Leak Sensor A', 'water leak', 'liters', 'active', (SELECT id FROM devices WHERE name = 'Device 7')),
  (gen_random_uuid(), 'Vibration Sensor A', 'vibration', 'g', 'active', (SELECT id FROM devices WHERE name = 'Device 8')),
  (gen_random_uuid(), 'Noise Sensor A', 'noise', 'dB', 'inactive', (SELECT id FROM devices WHERE name = 'Device 9')),
  (gen_random_uuid(), 'Light Sensor A', 'light', 'lux', 'active', (SELECT id FROM devices WHERE name = 'Device 10'));

  --sensor readings
  INSERT INTO sensor_readings (id, timestamp, value, unit, sensor_id, device_id)
VALUES
  (gen_random_uuid(), '2023-09-01 10:15:00', 27.5, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 10:00:00', 27.1, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 09:05:00', 25.5, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 09:03:00', 25.5, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 11:15:00', 33.1, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 03:05:00', 10.2, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 23:09:00', 19.3, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 14:05:00', 22.5, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 12:15:00', 34.0, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-01 11:20:00', 15.8, 'ppm', (SELECT id FROM sensors WHERE name = 'Gas Sensor A'), (SELECT id FROM devices WHERE name = 'Device 1')),
  (gen_random_uuid(), '2023-09-02 09:30:00', 21.0, '°C', (SELECT id FROM sensors WHERE name = 'Temperature Sensor A'), (SELECT id FROM devices WHERE name = 'Device 4')),
  (gen_random_uuid(), '2023-09-02 10:45:00', 16.2, 'ppm', (SELECT id FROM sensors WHERE name = 'Humidity Sensor A'), (SELECT id FROM devices WHERE name = 'Device 2'));

  