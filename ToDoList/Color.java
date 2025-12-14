package ToDoList;

public class Color {
    public static String coloredStatus(Status s) {
        return switch (s) {
            case Status.PENDING -> "\u001B[32mPENDING\u001B[0m";       // hijau
            case Status.IN_PROGRESS -> "\u001B[33mIN PROGRESS\u001B[0m"; // kuning
            case Status.DONE -> "\u001B[31mDONE\u001B[0m";             // merah
        };
    }

    public static String coloredPriority(String p) {
        return switch (p.toLowerCase()) {
            case "high" -> "\u001B[31mHIGH\u001B[0m";
            case "medium" -> "\u001B[33mMEDIUM\u001B[0m";
            default -> "\u001B[32mEASY\u001B[0m";
        };
    }
    public static String coloredProgress(double percent) {
        int filled = (int)Math.round(percent / 10);
        String bar = "█".repeat(filled) + "░".repeat(10 - filled);
        if (percent < 50) return "\u001B[31m" + bar + "\u001B[0m";
        else if (percent < 80) return "\u001B[33m" + bar + "\u001B[0m";
        else return "\u001B[32m" + bar + "\u001B[0m";
    }
}
