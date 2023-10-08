/**
 * Author: Raju Nayak
 */

package homesafe.controller;

import homesafe.entity.VibrationLevel;

public class SensorSimulation {
    private final float[] tempData = {78.7f, 78.2f, 78.0f};
    private final float[] humidData = {0.21f, 0.22f, 0.21f};
    private final float[] powerData = {0.9f, 0.85f, 0.8f};

    public void startSimulation() {
        new Thread(() -> {
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
        }).start();
    }

    public void simulateVibration(VibrationLevel level) {
        VibrationSensorController.getInstance().setLevel(level);
    }

    private int getMinArrayLength() {
        return Math.min(Math.min(tempData.length, humidData.length), powerData.length);
    }
}
