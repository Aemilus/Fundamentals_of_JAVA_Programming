package me.academy.javaprogrammer.week03.workshop;

import java.util.Arrays;

/**
 * Write code for a Radio class which implements a simplified version of a radio device.
 * - it has a predefined list of radio channels
 * - can be turned on/off
 * - volume can be increased/decreased
 * - you can switch to next/previous radio channels:
 *
 * Methods:
 * - turnOn()
 * - turnOff()
 * - increaseVolume()
 * - increaseVolume(int units)
 * - decreaseVolume()
 * - decreaseVolume(int units)
 * - nextRadioChannel()
 * - previousRadioChannel()
 * - status() -> displays the current status of the radio device (on or off) and if it's on then display current radio channel and volume
 * - constructor Radio(int maxUnits) -> each radio device has it's own maximum volume built-in
 *
 * Fields:
 * - volume
 * - poweredOn
 * - radioChannels (array/list of predefined radio channels)
 * - maxVolume (initialized by constructor)
 * - currentRadioChannel (index of current radio channel in the list of radio channels)
 *
 * Constraints:
 * - each method must print a message for the success or insuccess of method's execution
 * - all methods must perform all needed checks to prevent the radio device to enter an invalid state
 *   (for example: volume can't go higher than maxVolume, volume and currentRadioChannel can't be modified while radio device is turned off)
 * - when turned on the volume is set to 1; when turned off is set to 0
 * - reuse code for overloaded methods (identify where one of the overloaded methods is a particular case of the other)
 */

public class Radio {
    private final String[] RADIO_STATIONS = new String[]{"Kiss FM", "Radio Zu", "Radio 21", "Europa FM", "Virgin Radio"};
    private final int MAX_VOLUME;
    private int volume;
    private boolean poweredOn;
    private int currentRadioStation;

    public Radio(int maxVolume) {
        if (maxVolume < 1) {
            throw new IllegalArgumentException("Error: Radio device can't be created. Argument 'maxVolume' must be a positive integer greater than 0.");
        }
        MAX_VOLUME = maxVolume;
        System.out.println("A new radio.");
        System.out.println("List of supported channels: " + Arrays.toString(RADIO_STATIONS));
    }

    public void status() {
        System.out.printf("Power:\t\t%s\n", poweredOn ? "ON" : "OFF");
        System.out.printf("Volume:\t\t%d\n", volume);
        System.out.printf("Station:\t%s\n", RADIO_STATIONS[currentRadioStation]);
    }

    public void turnOn() {
        if (poweredOn) {
            System.out.println("Error: Radio device is already ON.");
        } else {
            poweredOn = true;
            volume = 1;
            System.out.println("Radio device turned ON.");
        }
    }

    public void turnOff() {
        if (!poweredOn) {
            System.out.println("Error: Radio device is already OFF.");
        } else {
            poweredOn = false;
            volume = 0;
            System.out.println("Radio device turned off.");
        }
    }

    public void increaseVolume() {
        increaseVolume(1);
    }

    public void increaseVolume(int units) {
        // checking for errors
        if (units < 1) {
            System.out.println("Error: Invalid volume increase request.");
            return;
        }
        if (!poweredOn) {
            System.out.println("Error: Volume can't be changed while radio device is turned off.");
            return;
        }
        if (volume == MAX_VOLUME) {
            System.out.println("Warning: Volume is at maximum. Can't increase further.");
            return;
        }
        // error checking done
        if (volume < MAX_VOLUME) {
            if (volume + units >= MAX_VOLUME) {
                // if the volume increase goes to or past max volume then set the volume to max.
                volume = MAX_VOLUME;
                System.out.print("Volume set to maximum. ");
            } else {
                volume += units;
                System.out.printf("Volume increased by %d units. ", units);
            }
            System.out.printf("Volume is %d.\n", volume);
        }
    }

    public void decreaseVolume() {
        decreaseVolume(1);
    }

    public void decreaseVolume(int units) {
        // checking for errors
        if (units < 1) {
            System.out.println("Error: Invalid volume decrease request.");
            return;
        }
        if (!poweredOn) {
            System.out.println("Error: Volume can't be changed while radio device is turned off.");
            return;
        }
        if (volume < 1) {
            System.out.println("Warning: Volume is at minimum. Can't decrease further.");
            return;
        }
        // error checking done
        if (volume - units < 0) {
            // if the volume would become negative set it to 0.
            volume = 0;
            System.out.print("Volume set to minimum. ");
        } else {
            volume -= units;
            System.out.printf("Volume decreased by %d units. ", units);
        }
        System.out.printf("Volume is %d.\n", volume);
    }

    public void nextRadioStation() {
        if (!poweredOn) {
            System.out.println("Error: Radio station can't be changed while radio device is turned off.");
        } else {
            // change to next index
            // note: when we hit after end of radio stations list then move to beginning of list
            currentRadioStation = ++currentRadioStation % RADIO_STATIONS.length;
            System.out.printf("Moved to next channel: %s.\n", RADIO_STATIONS[currentRadioStation]);
        }
    }

    public void previousRadioStation() {
        if (!poweredOn) {
            System.out.println("Error: Radio station can't be changed while radio device is turned off.");
            return;
        }
        // change to previous index
        // note: when we hit before start of radio stations list then move to end of list
        if (currentRadioStation == 0) {
            currentRadioStation = RADIO_STATIONS.length - 1;
        } else {
            currentRadioStation--;
        }
        // if you don;t like the if-else above then see below shorter version
        // currentRadioStation = (currentRadioStation == 0) ? RADIO_STATIONS.length - 1 : currentRadioStation - 1;
        System.out.printf("Moved to previous channel: %s.\n", RADIO_STATIONS[currentRadioStation]);
    }

    public static void main(String[] args) {
        // i have a broken radio; if you uncomment below line the program throws an exception and terminates
        // Radio myOldRadio = new Radio(-20);

        // purchase a new radio
        Radio myRadio = new Radio(100);
        myRadio.status();
        // test the errors displayed while trying to use the radio turned off
        myRadio.increaseVolume();
        myRadio.increaseVolume(50);
        myRadio.decreaseVolume();
        myRadio.decreaseVolume(50);
        myRadio.nextRadioStation();
        myRadio.previousRadioStation();
        // turn on new radio
        myRadio.turnOn();
        myRadio.turnOn();
        myRadio.status();
        // increase volume
        myRadio.increaseVolume();
        myRadio.increaseVolume(50);
        myRadio.increaseVolume(50);
        myRadio.increaseVolume(50);
        myRadio.status();
        // next channels
        for (int i = 0; i < 10; i++) {
            myRadio.nextRadioStation();
        }
        // previous channels
        for (int i = 0; i < 10; i++) {
            myRadio.previousRadioStation();
        }
        // decrease volume
        myRadio.decreaseVolume();
        myRadio.decreaseVolume(50);
        myRadio.decreaseVolume(50);
        myRadio.decreaseVolume(50);
        // turn it off
        myRadio.turnOff();
        myRadio.turnOff();
        myRadio.status();
    }
}
