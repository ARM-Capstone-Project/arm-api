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

----added columns
INSERT INTO devices (id, name, batch_no, description, location, status, tag_no, type, zone_id)
VALUES
  (gen_random_uuid(), 'Device 1', 'Batch123', 'Gas Detector', 'Building A', 'active','D11', 'detector',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 1)),
  (gen_random_uuid(), 'Device 2', 'Batch124', 'Temperature Sensor', 'Building B', 'active', 'D12','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 2)),
  (gen_random_uuid(), 'Device 3', 'Batch125', 'Humidity Sensor', 'Building C', 'inactive', 'D13','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 3)),
  (gen_random_uuid(), 'Device 4', 'Batch126', 'Smoke Detector', 'Building D','active', 'D14','detector',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 4)),
  (gen_random_uuid(), 'Device 5', 'Batch127', 'Pressure Sensor', 'Building E', 'active', 'D15','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 5)),
  (gen_random_uuid(), 'Device 6', 'Batch128', 'CO2 Sensor', 'Building F', 'inactive', 'D16','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 6)),
  (gen_random_uuid(), 'Device 7', 'Batch129', 'Water Leak Detector', 'Building G', 'active', 'D17','detector',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 7)),
  (gen_random_uuid(), 'Device 8', 'Batch130', 'Vibration Sensor', 'Building H', 'active', 'D18','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 8)),
  (gen_random_uuid(), 'Device 9', 'Batch131', 'Noise Sensor', 'Building I', 'inactive', 'D19','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 9)),
  (gen_random_uuid(), 'Device 10', 'Batch132', 'Light Sensor', 'Building J','active', 'D20','sensor',
    (SELECT id FROM (SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM zones) AS x WHERE x.rn = 10));
    