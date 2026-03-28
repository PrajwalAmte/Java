# Java Learning Repo

A hands-on reference covering Core Java fundamentals, SOLID principles, and Gang-of-Four design patterns — each topic in a self-contained, runnable file.

## Structure

```
├── core-java/
│   ├── basics/
│   │   ├── DataTypes.java          — Primitives, casting, wrapper classes
│   │   ├── ControlFlow.java        — if/else, switch, loops, break/continue
│   │   ├── ArraysDemo.java         — 1D/2D arrays, sort, search, transpose
│   │   ├── Strings.java            — String methods, StringBuilder, comparison
│   │   └── Recursion.java          — Factorial, Fibonacci, binary search, Tower of Hanoi
│   ├── oop/
│   │   ├── Inheritance.java        — extends, super, method overriding, upcasting
│   │   ├── Polymorphism.java       — Overloading (compile-time), overriding (runtime)
│   │   ├── Encapsulation.java      — Private fields, getters/setters, validation
│   │   └── Abstraction.java        — Abstract classes, interfaces, default methods
│   ├── collections/
│   │   ├── ListDemo.java           — ArrayList, LinkedList, Stack, Queue
│   │   ├── MapDemo.java            — HashMap, TreeMap, LinkedHashMap, computeIfAbsent
│   │   └── SetDemo.java            — HashSet, TreeSet, LinkedHashSet, set operations
│   ├── exceptions/
│   │   └── ExceptionHandling.java  — try/catch/finally, multi-catch, custom exceptions, try-with-resources
│   ├── generics/
│   │   └── Generics.java           — Generic classes, methods, bounded types, wildcards
│   ├── lambdas-streams/
│   │   └── LambdasStreams.java      — Lambda expressions, functional interfaces, Stream API
│   ├── file-io/
│   │   └── FileIO.java             — java.nio.file: read, write, append, copy, delete
│   └── multithreading/
│       └── Multithreading.java     — Thread, Runnable, AtomicInteger, synchronized, ExecutorService
│
├── solid/
│   ├── SRP.java    — Single Responsibility Principle
│   ├── OCP.java    — Open/Closed Principle
│   ├── LSP.java    — Liskov Substitution Principle
│   ├── ISP.java    — Interface Segregation Principle
│   └── DIP.java    — Dependency Inversion Principle
│
└── design-patterns/
    ├── creational/
    │   ├── Singleton.java      — Double-checked locking + Enum singleton
    │   ├── FactoryMethod.java  — Simple factory + Abstract factory
    │   ├── BuilderDemo.java    — Fluent builder + validation
    │   └── Prototype.java      — Shallow vs deep clone, registry
    ├── structural/
    │   ├── Adapter.java        — Object adapter (composition-based)
    │   ├── Decorator.java      — Composable decorators (Coffee example)
    │   └── Facade.java         — Simplified interface over complex subsystem
    └── behavioral/
        ├── Strategy.java       — Interchangeable algorithms (sort + payment)
        ├── State.java          — State machine (order lifecycle + traffic light)
        ├── Observer.java       — Event bus, stock market watcher
        ├── Command.java        — Undo/redo, macro commands (text editor)
        └── TemplateMethod.java — Algorithm skeleton (data migration + game AI)
```

## How to Run

Each file is self-contained — no build tool required. Compile and run from the file's directory:

```bash
# Example
cd core-java/basics
javac DataTypes.java && java DataTypes

cd ../../design-patterns/behavioral
javac Strategy.java && java Strategy
```

## Topics at a Glance

| Area | Key Concepts |
|------|-------------|
| **Basics** | Primitives, casting, control flow, arrays, strings, recursion |
| **OOP** | Inheritance, polymorphism, encapsulation, abstraction |
| **Collections** | List, Map, Set — all major implementations |
| **Exceptions** | Checked/unchecked, custom, try-with-resources, chaining |
| **Generics** | Generic classes/methods, bounded types, wildcards |
| **Lambdas & Streams** | Functional interfaces, method references, Stream API |
| **File I/O** | NIO.2 (`java.nio.file`), BufferedReader/Writer |
| **Multithreading** | Thread, AtomicInteger, synchronized, ExecutorService |
| **SOLID** | All 5 principles — each with a bad vs good example |
| **Creational Patterns** | Singleton, Factory Method, Builder, Prototype |
| **Structural Patterns** | Adapter, Decorator, Facade |
| **Behavioral Patterns** | Strategy, State, Observer, Command, Template Method |
