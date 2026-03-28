// Template Method Pattern
// Define the skeleton of an algorithm in a base class,
// deferring some steps to subclasses. Subclasses can override
// specific steps without changing the algorithm's structure.

abstract class DataMigrator {
    // Template method — final so subclasses can't change the order of steps
    final void migrate() {
        System.out.println("\n[" + getClass().getSimpleName() + "] Starting migration");
        connect();
        fetchData();
        transformData();   // hook — may be overridden
        validateData();
        loadData();
        disconnect();
        System.out.println("[" + getClass().getSimpleName() + "] Migration complete");
    }

    abstract void connect();
    abstract void fetchData();
    abstract void loadData();

    // Default implementation — subclasses may override
    void transformData() { System.out.println("  No transformation"); }
    void validateData()  { System.out.println("  Validating..."); }
    void disconnect()    { System.out.println("  Disconnected"); }
}

class MySQLToPostgresMigrator extends DataMigrator {
    @Override void connect()   { System.out.println("  Connected to MySQL + PostgreSQL"); }
    @Override void fetchData() { System.out.println("  Fetched rows from MySQL"); }
    @Override void loadData()  { System.out.println("  Inserted rows into PostgreSQL"); }
    @Override void transformData() { System.out.println("  Converting MySQL types to PostgreSQL types"); }
}

class CSVToDBMigrator extends DataMigrator {
    @Override void connect()   { System.out.println("  Opened CSV file + DB connection"); }
    @Override void fetchData() { System.out.println("  Parsed CSV rows"); }
    @Override void loadData()  { System.out.println("  Bulk inserted into database"); }
    @Override void transformData() { System.out.println("  Cleaning and normalizing CSV fields"); }
}

class APIToDBMigrator extends DataMigrator {
    @Override void connect()   { System.out.println("  Authenticated with REST API"); }
    @Override void fetchData() { System.out.println("  Paged through API responses"); }
    @Override void loadData()  { System.out.println("  Upserted records into database"); }
    // uses default transformData and validateData
}

// Another example: game AI turns
abstract class GameAI {
    final void takeTurn() {
        collectResources();
        buildStructures();
        buildUnits();
        attack();
    }

    void collectResources() { System.out.println(name() + ": collecting resources"); }
    void buildStructures()  { System.out.println(name() + ": building structures"); }
    abstract void buildUnits();
    abstract void attack();
    abstract String name();
}

class OrcsAI extends GameAI {
    @Override public String name() { return "Orcs"; }
    @Override public void buildUnits() { System.out.println("Orcs: training grunts"); }
    @Override public void attack()     { System.out.println("Orcs: charging the enemy!"); }
}

class HumansAI extends GameAI {
    @Override public String name() { return "Humans"; }
    @Override public void buildUnits() { System.out.println("Humans: recruiting knights"); }
    @Override public void attack()     { System.out.println("Humans: strategic assault"); }
    @Override public void buildStructures() { System.out.println("Humans: building castle"); }
}

public class TemplateMethod {
    public static void main(String[] args) {
        System.out.println("=== Data Migration ===");
        DataMigrator[] migrators = {
            new MySQLToPostgresMigrator(),
            new CSVToDBMigrator(),
            new APIToDBMigrator()
        };
        for (DataMigrator m : migrators) m.migrate();

        System.out.println("\n=== Game AI Turns ===");
        GameAI[] ais = {new OrcsAI(), new HumansAI()};
        for (GameAI ai : ais) { ai.takeTurn(); System.out.println(); }
    }
}
