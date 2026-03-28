public class DataTypes {
    public static void main(String[] args) {
        // Primitive types
        byte b = 127;
        short s = 32767;
        int i = 2_147_483_647;
        long l = 9_223_372_036_854_775_807L;
        float f = 3.14f;
        double d = 3.14159265358979;
        char c = 'A';
        boolean flag = true;

        System.out.println("byte: " + b + ", short: " + s);
        System.out.println("int: " + i + ", long: " + l);
        System.out.println("float: " + f + ", double: " + d);
        System.out.println("char: " + c + ", boolean: " + flag);

        // Widening (automatic) and narrowing (explicit) casting
        double widened = i;
        int narrowed = (int) 9.99;
        System.out.println("Widened int->double: " + widened);
        System.out.println("Narrowed double->int: " + narrowed);

        // Wrapper classes and auto-boxing/unboxing
        Integer wrapped = 42;
        int unboxed = wrapped;
        System.out.println("Auto-boxed: " + wrapped + ", unboxed: " + unboxed);

        // String conversions
        String str = String.valueOf(3.14);
        int parsed = Integer.parseInt("200");
        System.out.println("valueOf: " + str + ", parseInt: " + parsed);

        // Integer utility methods
        System.out.println("Max int: " + Integer.MAX_VALUE);
        System.out.println("Binary of 42: " + Integer.toBinaryString(42));
        System.out.println("Hex of 255: " + Integer.toHexString(255));
    }
}
