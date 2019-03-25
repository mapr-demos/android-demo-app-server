# Android Demo App Server
REST server for [Android Demo App](https://github.com/mapr-demos/android-demo-app)
## Quick start
Start application:
```bash
./gradlew bootRun
```
Possible properties:
* KAFKA_FOLDER - folder where streams are storing (default `/`)
* KAFKA_STREAM - name of stream (default `mobile`)
* SECURITY_USERNAME - username for mobile application (default `mapr`)
* SECURITY_PASSWORD - password for mobile application (default `mapr`)

Start with properties:
```bash
    KAFKA_FOLDER=YourPathToStreamFolder KAFKA_STREAM=YourStreamName SECURITY_USERNAME=YourUsername SECURITY_PASSWORD=YourPassword ./gradlew bootRun
```
## Topics in Kafka
* Accelerometer topic: `YourPathToStreamFolder/YourStreamName:accelerometer_YourAndroidId`
* Location topic: `YourPathToStreamFolder/YourStreamName:location_YourAndroidId`
* Photos topic: `YourPathToStreamFolder/YourStreamName:photo`
## Data examples
* Accelerometer data:
```json
{
  "androidId": "ANDROID_ID",
  "date": 1553598171,
  "accelerometer": [0.6485596,4.778183,8.668915]
}
```
* Location data:
```json
{
  "androidId": "ANDROID_ID",
  "date": 1553598171,
  "latitude": 31.0000,
  "longitude": 31.0000
}
```
* Photo data:
```json
{
  "androidId": "ANDROID_ID",
  "date": 1553598171,
  "format": "jpeg",
  "photo": []
}
```