import java.util.ArrayDeque;
import java.util.Deque;

// Command Pattern
// Encapsulate a request as an object.
// Enables undo/redo, queuing, logging, and macro commands.

interface Cmd {
    void execute();
    void undo();
    String description();
}

// Receiver
class TextDocument {
    private final StringBuilder content = new StringBuilder();

    void insert(String text) { content.append(text); }
    void delete(int count)   { int len = content.length(); content.delete(Math.max(0, len - count), len); }
    String getContent()      { return content.toString(); }
}

// Concrete commands
class InsertCmd implements Cmd {
    private final TextDocument doc;
    private final String text;

    InsertCmd(TextDocument doc, String text) { this.doc = doc; this.text = text; }
    @Override public void execute()           { doc.insert(text); }
    @Override public void undo()              { doc.delete(text.length()); }
    @Override public String description()     { return "Insert: \"" + text + "\""; }
}

class DeleteCmd implements Cmd {
    private final TextDocument doc;
    private final int count;
    private String deleted;

    DeleteCmd(TextDocument doc, int count) { this.doc = doc; this.count = count; }

    @Override
    public void execute() {
        String content = doc.getContent();
        deleted = content.substring(Math.max(0, content.length() - count));
        doc.delete(count);
    }

    @Override public void undo()          { doc.insert(deleted); }
    @Override public String description() { return "Delete last " + count + " chars"; }
}

// Invoker: manages command execution and history
class CommandHistory {
    private final Deque<Cmd> history = new ArrayDeque<>();
    private final Deque<Cmd> redoStack = new ArrayDeque<>();

    void execute(Cmd cmd) {
        cmd.execute();
        history.push(cmd);
        redoStack.clear();
        System.out.println("Executed: " + cmd.description());
    }

    void undo() {
        if (history.isEmpty()) { System.out.println("Nothing to undo"); return; }
        Cmd cmd = history.pop();
        cmd.undo();
        redoStack.push(cmd);
        System.out.println("Undid: " + cmd.description());
    }

    void redo() {
        if (redoStack.isEmpty()) { System.out.println("Nothing to redo"); return; }
        Cmd cmd = redoStack.pop();
        cmd.execute();
        history.push(cmd);
        System.out.println("Redid: " + cmd.description());
    }
}

// Macro command: composite of multiple commands
class MacroCmd implements Cmd {
    private final java.util.List<Cmd> commands;
    private final String name;

    MacroCmd(String name, Cmd... cmds) { this.name = name; this.commands = java.util.List.of(cmds); }

    @Override public void execute() { commands.forEach(Cmd::execute); }
    @Override public void undo()    { for (int i = commands.size() - 1; i >= 0; i--) commands.get(i).undo(); }
    @Override public String description() { return "Macro: " + name; }
}

public class Command {
    static void print(TextDocument doc) { System.out.println("Content: \"" + doc.getContent() + "\""); }

    public static void main(String[] args) {
        TextDocument doc = new TextDocument();
        CommandHistory history = new CommandHistory();

        history.execute(new InsertCmd(doc, "Hello"));
        print(doc);

        history.execute(new InsertCmd(doc, ", World"));
        print(doc);

        history.execute(new InsertCmd(doc, "!"));
        print(doc);

        System.out.println("\n-- Undo --");
        history.undo(); print(doc);
        history.undo(); print(doc);

        System.out.println("\n-- Redo --");
        history.redo(); print(doc);

        System.out.println("\n-- Delete --");
        history.execute(new DeleteCmd(doc, 6));
        print(doc);
        history.undo(); print(doc);

        System.out.println("\n-- Macro --");
        TextDocument doc2 = new TextDocument();
        CommandHistory h2 = new CommandHistory();
        MacroCmd format = new MacroCmd("greeting",
            new InsertCmd(doc2, "Dear User,\n"),
            new InsertCmd(doc2, "Welcome to the system!\n"),
            new InsertCmd(doc2, "Regards, Team\n")
        );
        h2.execute(format);
        System.out.println(doc2.getContent());
        h2.undo();
        System.out.println("After undo: \"" + doc2.getContent() + "\"");
    }
}
