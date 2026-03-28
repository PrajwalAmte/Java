// Factory Method Pattern
// Define an interface for creating objects; let subclasses/factory decide which class to instantiate.
// Decouples object creation from usage.

interface Button {
    void render();
    void onClick();
}

class WindowsButton implements Button {
    @Override public void render() { System.out.println("Rendering Windows button"); }
    @Override public void onClick() { System.out.println("Windows button clicked"); }
}

class MacButton implements Button {
    @Override public void render() { System.out.println("Rendering Mac button"); }
    @Override public void onClick() { System.out.println("Mac button clicked"); }
}

class LinuxButton implements Button {
    @Override public void render() { System.out.println("Rendering Linux button"); }
    @Override public void onClick() { System.out.println("Linux button clicked"); }
}

// Factory: centralizes creation logic
class ButtonFactory {
    static Button create(String os) {
        return switch (os.toLowerCase()) {
            case "windows" -> new WindowsButton();
            case "mac"     -> new MacButton();
            case "linux"   -> new LinuxButton();
            default -> throw new IllegalArgumentException("Unsupported OS: " + os);
        };
    }
}

// Abstract Factory variant: dialog factory that creates families of related objects
interface Checkbox {
    void render();
}
class WindowsCheckbox implements Checkbox { @Override public void render() { System.out.println("Windows checkbox"); } }
class MacCheckbox implements Checkbox { @Override public void render() { System.out.println("Mac checkbox"); } }

abstract class UIFactory {
    abstract Button createButton();
    abstract Checkbox createCheckbox();
    void renderUI() { createButton().render(); createCheckbox().render(); }
}

class WindowsFactory extends UIFactory {
    @Override public Button createButton() { return new WindowsButton(); }
    @Override public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacFactory extends UIFactory {
    @Override public Button createButton() { return new MacButton(); }
    @Override public Checkbox createCheckbox() { return new MacCheckbox(); }
}

public class FactoryMethod {
    public static void main(String[] args) {
        // Simple factory
        String[] platforms = {"windows", "mac", "linux"};
        for (String os : platforms) {
            Button btn = ButtonFactory.create(os);
            btn.render();
            btn.onClick();
        }

        System.out.println();

        // Abstract factory
        UIFactory[] factories = {new WindowsFactory(), new MacFactory()};
        for (UIFactory factory : factories) {
            System.out.println(factory.getClass().getSimpleName() + ":");
            factory.renderUI();
        }
    }
}
