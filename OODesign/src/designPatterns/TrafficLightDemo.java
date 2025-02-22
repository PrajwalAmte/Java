/**
 * The State Pattern is a behavioral design pattern that allows an object to alter its behavior when its internal state changes, making it appear as if the object has changed its class. 
 * It encapsulates different states into separate classes and delegates state transitions to these classes, reducing complex conditional logic.
 */
package designPatterns;
import java.util.Scanner;

public class TrafficLightDemo {
	public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Press Enter to change the traffic light (or type 'exit' to quit)...");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Traffic Light Simulation...");
                break;
            }
            trafficLight.changeLight();
        }
        scanner.close();
    }
}

//Context Class
class TrafficLight {
 private LightState state = new RedLight();  // Initial state

 public void setState(LightState state) {
     this.state = state;
 }

 public void changeLight() {
     state.handleStateChange(this);
 }
}

//Abstract State Class
abstract class LightState {
 abstract void handleStateChange(TrafficLight trafficLight);
}

//Concrete State: Red Light
class RedLight extends LightState {
 @Override
 void handleStateChange(TrafficLight trafficLight) {
     System.out.println("Red Light - Stop!");
     trafficLight.setState(new GreenLight());
 }
}

//Concrete State: Green Light
class GreenLight extends LightState {
 @Override
 void handleStateChange(TrafficLight trafficLight) {
     System.out.println("Green Light - Go!");
     trafficLight.setState(new YellowLight());
 }
}

//Concrete State: Yellow Light
class YellowLight extends LightState {
 @Override
 void handleStateChange(TrafficLight trafficLight) {
     System.out.println("Yellow Light - Slow down...");
     trafficLight.setState(new RedLight());
 }
}
