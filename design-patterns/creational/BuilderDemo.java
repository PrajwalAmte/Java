// Builder Pattern
// Construct complex objects step by step.
// Separates construction from representation; allows different configurations.

class Pizza {
    private final String size;
    private final String crust;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    private final boolean extraSauce;

    private Pizza(PizzaBuilder b) {
        this.size       = b.size;
        this.crust      = b.crust;
        this.cheese     = b.cheese;
        this.pepperoni  = b.pepperoni;
        this.mushrooms  = b.mushrooms;
        this.extraSauce = b.extraSauce;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size + " pizza, " + crust + " crust");
        if (cheese)     sb.append(", cheese");
        if (pepperoni)  sb.append(", pepperoni");
        if (mushrooms)  sb.append(", mushrooms");
        if (extraSauce) sb.append(", extra sauce");
        return sb.toString();
    }

    static class PizzaBuilder {
        private final String size;
        private String crust = "thin";
        private boolean cheese, pepperoni, mushrooms, extraSauce;

        PizzaBuilder(String size) { this.size = size; }

        PizzaBuilder crust(String c)  { this.crust = c;       return this; }
        PizzaBuilder cheese()         { this.cheese = true;    return this; }
        PizzaBuilder pepperoni()      { this.pepperoni = true; return this; }
        PizzaBuilder mushrooms()      { this.mushrooms = true; return this; }
        PizzaBuilder extraSauce()     { this.extraSauce = true; return this; }

        Pizza build() { return new Pizza(this); }
    }
}

// Builder with mandatory validation
class HttpRequest {
    private final String url;
    private final String method;
    private final java.util.Map<String, String> headers;
    private final String body;

    private HttpRequest(Builder b) {
        this.url     = b.url;
        this.method  = b.method;
        this.headers = java.util.Collections.unmodifiableMap(b.headers);
        this.body    = b.body;
    }

    @Override
    public String toString() {
        return method + " " + url + " | headers=" + headers + (body != null ? " | body=" + body : "");
    }

    static class Builder {
        private final String url;
        private String method = "GET";
        private final java.util.Map<String, String> headers = new java.util.LinkedHashMap<>();
        private String body;

        Builder(String url) {
            if (url == null || url.isBlank()) throw new IllegalArgumentException("URL required");
            this.url = url;
        }

        Builder method(String m)             { this.method = m;           return this; }
        Builder header(String k, String v)   { this.headers.put(k, v);    return this; }
        Builder body(String b)               { this.body = b;             return this; }
        HttpRequest build()                  { return new HttpRequest(this); }
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        // Pizza builder
        Pizza p1 = new Pizza.PizzaBuilder("Large").crust("thick").cheese().pepperoni().build();
        Pizza p2 = new Pizza.PizzaBuilder("Medium").mushrooms().cheese().extraSauce().build();
        Pizza p3 = new Pizza.PizzaBuilder("Small").build();  // plain, all defaults
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println();

        // HTTP request builder
        HttpRequest get = new HttpRequest.Builder("https://api.example.com/users")
            .header("Accept", "application/json")
            .header("Authorization", "Bearer token123")
            .build();

        HttpRequest post = new HttpRequest.Builder("https://api.example.com/users")
            .method("POST")
            .header("Content-Type", "application/json")
            .body("{\"name\":\"Alice\"}")
            .build();

        System.out.println(get);
        System.out.println(post);
    }
}
