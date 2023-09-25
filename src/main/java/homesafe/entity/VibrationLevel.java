package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public enum VibrationLevel {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    EXTREME(4);

    private final int value;

    VibrationLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
