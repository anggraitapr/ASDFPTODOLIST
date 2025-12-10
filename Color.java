public class Color {
    public static String coloredStatus(Status s) {
        return switch (s) {
            case PENDING -> "\u001B[32mPENDING\u001B[0m";       // hijau
            case IN_PROGRESS -> "\u001B[33mIN PROGRESS\u001B[0m"; // kuning
            case DONE -> "\u001B[31mDONE\u001B[0m";             // merah
        };
    }

    public static String coloredPriority(String p) {
        return switch (p.toLowerCase()) {
            case "high" -> "\u001B[31mHIGH\u001B[0m";
            case "medium" -> "\u001B[33mMEDIUM\u001B[0m";
            default -> "\u001B[32mEASY\u001B[0m";
        };
    }
}
