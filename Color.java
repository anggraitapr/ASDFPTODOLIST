public class Color {
    public static String coloredPriority(String p) {
        if (p == null) return "";
        p = p.trim().toLowerCase();
        return switch (p) {
            case "high" -> "\u001B[31mHIGH\u001B[0m";     // merah
            case "medium" -> "\u001B[33mMEDIUM\u001B[0m"; // kuning
            case "easy" -> "\u001B[32mEASY\u001B[0m";     // hijau
            default -> p;
        };
    }
}
