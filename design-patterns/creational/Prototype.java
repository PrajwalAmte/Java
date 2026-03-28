// Prototype Pattern
// Create new objects by cloning an existing object (the prototype).
// Useful when object creation is expensive or complex.

class GameCharacter implements Cloneable {
    private String name;
    private String characterClass;
    private int health;
    private int attack;
    private java.util.List<String> inventory;

    GameCharacter(String name, String characterClass, int health, int attack) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.attack = attack;
        this.inventory = new java.util.ArrayList<>();
    }

    void addItem(String item) { inventory.add(item); }
    void setName(String name) { this.name = name; }
    void setHealth(int hp) { this.health = hp; }

    // Shallow clone — inventory list is shared (reference copy)
    public GameCharacter shallowClone() {
        try { return (GameCharacter) super.clone(); }
        catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
    }

    // Deep clone — inventory list is copied independently
    public GameCharacter deepClone() {
        GameCharacter clone = shallowClone();
        clone.inventory = new java.util.ArrayList<>(this.inventory);
        return clone;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] HP=%d ATK=%d items=%s", name, characterClass, health, attack, inventory);
    }
}

class CharacterRegistry {
    private final java.util.Map<String, GameCharacter> templates = new java.util.HashMap<>();

    void register(String key, GameCharacter template) { templates.put(key, template); }

    GameCharacter spawn(String key) {
        GameCharacter template = templates.get(key);
        if (template == null) throw new IllegalArgumentException("No template: " + key);
        return template.deepClone();
    }
}

public class Prototype {
    public static void main(String[] args) {
        // Base prototype
        GameCharacter warriorTemplate = new GameCharacter("Warrior", "Fighter", 200, 50);
        warriorTemplate.addItem("Sword");
        warriorTemplate.addItem("Shield");

        System.out.println("Template: " + warriorTemplate);

        // Deep clone — changes don't affect original
        GameCharacter w1 = warriorTemplate.deepClone();
        w1.setName("Thorin");
        w1.addItem("Helmet");

        GameCharacter w2 = warriorTemplate.deepClone();
        w2.setName("Dwalin");
        w2.setHealth(250);

        System.out.println("Clone 1: " + w1);
        System.out.println("Clone 2: " + w2);
        System.out.println("Template unchanged: " + warriorTemplate);

        System.out.println();

        // Registry
        CharacterRegistry registry = new CharacterRegistry();
        GameCharacter mageTemplate = new GameCharacter("Mage", "Caster", 120, 90);
        mageTemplate.addItem("Staff");
        registry.register("warrior", warriorTemplate);
        registry.register("mage", mageTemplate);

        GameCharacter fighter = registry.spawn("warrior");
        fighter.setName("Balin");
        System.out.println("Spawned: " + fighter);

        GameCharacter spellcaster = registry.spawn("mage");
        spellcaster.setName("Gandalf");
        spellcaster.addItem("Tome");
        System.out.println("Spawned: " + spellcaster);
    }
}
