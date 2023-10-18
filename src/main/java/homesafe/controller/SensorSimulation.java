/**
 * Author: Raju Nayak
 */

package homesafe.controller;

import homesafe.entity.VibrationLevel;

public class SensorSimulation {
    private final float[] tempData = {72.0f, 73.0f, 74.0f, 75.0f, 78.7f, 78.2f, 78.0f, 90.0f, 100.0f, 76.0f};
    private final float[] humidData = {20.35f, 22.18f, 19.42f, 21.75f, 23.12f, 18.93f, 20.87f, 24.0f, 18.21f, 22.67f};
    private final float[] powerData = {47.0f, 18.0f, 63.0f, 82.0f, 35.0f, 91.0f, 55.0f, 74.0f, 6.0f, 29.0f};

    public void startSimulation() {

        for (int i = 0; i < getMinArrayLength(); i++) {

            TemperatureSensorController.getInstance().setTemperature(tempData[i]);
            HumiditySensorController.getInstance().setHumidity(humidData[i]);
            PowerSensorController.getInstance().setPowerLevel(powerData[i]);

            // Intentional delay between processing sensor data
            try {
                Thread.sleep(30000); // Pause for 30 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void simulateVibration(VibrationLevel level) {
        VibrationSensorController.getInstance().setLevel(level);
    }

    private int getMinArrayLength() {
        return Math.min(Math.min(tempData.length, humidData.length), powerData.length);
    }
}
