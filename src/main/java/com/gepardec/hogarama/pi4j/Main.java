package com.gepardec.hogarama.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;


public class Main {
  final static GpioController gpio = GpioFactory.getInstance();
  
  public static void main(String args[]) {
    System.out.println("Hello World");
    GpioPinDigitalInput myButton = getProvisionedPin(RaspiPin.GPIO_02);
    myButton.addListener(new GpioUsageExampleListener());
  }
  
  private static GpioPinDigitalInput getProvisionedPin(Pin pin) {
    return gpio.provisionDigitalInputPin(pin,             // PIN NUMBER
      "My Finger",                   // PIN FRIENDLY NAME (optional)
      PinPullResistance.PULL_DOWN); // PIN RESISTANCE (optional)
  }
  
  
  private static class GpioUsageExampleListener implements GpioPinListenerDigital {
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        // display pin state on console
        System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = "
                + event.getState());
    }
  }

}