import java.time.LocalDate;
import java.util.*;

class Task {
    String title;
    LocalDate deadline;
    List<Task> subtasks = new ArrayList<>();

    Status status = Status.PENDING;
    Task(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    void addSubtask(Task sub) {
        subtasks.add(sub);
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Task> rootTasks = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== TO-DO LIST MENU =====");
            System.out.println("1. Tambah Task Utama");
            System.out.println("2. Tambah Subtask");
            System.out.println("3. Tampilkan Task (BFS - Level 1)");
            System.out.println("4. Tampilkan Task (DFS - Semua)");
            System.out.println("5. Tampilkan (Kombinasi)");
            System.out.println("6. Edit Task / Subtask");
            System.out.println("7. Hapus Task / Subtask");
            System.out.println("8. Exit");
            System.out.println("9. Tandai Task Selesai");
            System.out.print("Pilih menu: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> addRootTask();
                case 2 -> addSubtask();
                case 3 -> showBFSTopLevel();
                case 4 -> showDFSAll();
                case 5 -> showCombined();
                case 6 -> editMenu();
                case 7 -> deleteMenu();
                case 8 -> { System.out.println("Keluar..."); return; }
                case 9 -> markTaskDone();
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    // ============================================================
    //                   ADD ROOTS TASK
    // ============================================================
    static void addRootTask() {
        System.out.print("Judul task: ");
        String title = sc.nextLine();

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate dl = LocalDate.parse(sc.nextLine());

        rootTasks.add(new Task(title, dl));
        System.out.println("Task utama ditambahkan!");
    }

    // ============================================================
    //                   ADD SUBTASK LEVEL satu
    // ============================================================
    static void addSubtask() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task utama.");
            return;
        }

        System.out.println("\nDaftar Task Utama:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih task utama: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Pilihan salah");
            return;
        }

        System.out.print("Judul subtask: ");
        String title = sc.nextLine();

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate dl = LocalDate.parse(sc.nextLine());

        rootTasks.get(idx).addSubtask(new Task(title, dl));
        System.out.println("Subtask ditambahkan!");
    }

    // ============================================================
    //                   BFS LEVEL 1
    // ============================================================
    static void showBFSTopLevel() {
        System.out.println("\n=== BFS (Level 1) ===");

        rootTasks.sort(Comparator.comparing(t -> t.deadline));

        for (Task t : rootTasks) {
            System.out.println("- " + t.title + " (Deadline: " + t.deadline + ") " + progressBar(t.status));
        }
    }

    // ============================================================
    //                   DFS ALL LEVELS
    // ============================================================
    static void showDFSAll() {
        System.out.println("\n=== DFS (Semua Task) ===");

        rootTasks.sort(Comparator.comparing(t -> t.deadline));

        for (Task t : rootTasks) {
            dfs(t, 0);
        }
    }
    static void markTaskDone() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task.");
            return;
        }

        System.out.println("\nPilih task yang ingin ditandai selesai:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih nomor: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Pilihan salah.");
            return;
        }

        Task t = rootTasks.get(idx);
        t.status = Status.DONE;
        System.out.println("Task \"" + t.title + "\" ditandai selesai!");
    }
    static String progressBar(Status status) {
        switch (status) {
            case DONE:
                return Color.GREEN + "[##########] 100%" + Color.RESET;
            case IN_PROGRESS:
                return Color.YELLOW + "[#####-----] 50%" + Color.RESET;
            default:
                return Color.RED + "[##--------] 20%" + Color.RESET;
        }
    }


    static void dfs(Task t, int depth) {
        t.subtasks.sort(Comparator.comparing(s -> s.deadline));

        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "- " + t.title + " (deadline: " + t.deadline + ")");

        for (Task st : t.subtasks) {
            dfs(st, depth + 1);
        }
    }

    // ============================================================
    //                   KOMBINASI BFS + DFS
    // ============================================================
    static void showCombined() {
        System.out.println("\n=== Kombinasi BFS + DFS ===");

        rootTasks.sort(Comparator.comparing(t -> t.deadline));

        System.out.println("\n--- Tugas Level 1 (BFS) ---");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title
                    + " (deadline: " + rootTasks.get(i).deadline + ")");
        }

        System.out.print("\nLihat detail task nomor: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        System.out.println("\n--- Detail Task (DFS) ---");
        dfs(rootTasks.get(idx), 0);
    }

    // ============================================================
    //                   EDIT MENU
    // ============================================================
    static void editMenu() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task.");
            return;
        }

        System.out.println("\nEdit apa?");
        System.out.println("1. Task Utama");
        System.out.println("2. Subtask Level 1");
        System.out.print("Pilih: ");
        int c = sc.nextInt(); sc.nextLine();

        if (c == 1) editRootTask();
        else if (c == 2) editSubtask();
        else System.out.println("Pilihan salah.");
    }

    // ----- EDIT ROOT TASK -----
    static void editRootTask() {
        System.out.println("\nDaftar Task Utama:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih task: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        Task t = rootTasks.get(idx);

        System.out.print("Judul baru: ");
        t.title = sc.nextLine();

        System.out.print("Deadline baru (YYYY-MM-DD): ");
        t.deadline = LocalDate.parse(sc.nextLine());

        System.out.println("Task berhasil diedit!");
    }

    // ----- EDIT SUBTASK -----
    static void editSubtask() {
        System.out.println("\nDaftar Task Utama:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih task utama: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        Task parent = rootTasks.get(idx);

        if (parent.subtasks.isEmpty()) {
            System.out.println("Task ini tidak punya subtask.");
            return;
        }

        System.out.println("\nSubtask:");
        for (int i = 0; i < parent.subtasks.size(); i++) {
            System.out.println((i + 1) + ". " + parent.subtasks.get(i).title);
        }

        System.out.print("Pilih subtask: ");
        int sidx = sc.nextInt() - 1; sc.nextLine();

        if (sidx < 0 || sidx >= parent.subtasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        Task sub = parent.subtasks.get(sidx);

        System.out.print("Judul baru: ");
        sub.title = sc.nextLine();

        System.out.print("Deadline baru (YYYY-MM-DD): ");
        sub.deadline = LocalDate.parse(sc.nextLine());

        System.out.println("Subtask berhasil diedit!");
    }

    // ============================================================
    //                   DELETE MENU
    // ============================================================
    static void deleteMenu() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task.");
            return;
        }

        System.out.println("\nHapus apa?");
        System.out.println("1. Task Utama");
        System.out.println("2. Subtask Level 1");
        System.out.print("Pilih: ");
        int c = sc.nextInt(); sc.nextLine();

        if (c == 1) deleteRootTask();
        else if (c == 2) deleteSubtask();
        else System.out.println("Pilihan salah.");
    }

    // ----- DELETE ROOT TASK -----
    static void deleteRootTask() {
        System.out.println("\nDaftar Task Utama:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih task yang ingin dihapus: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        rootTasks.remove(idx);
        System.out.println("Task utama berhasil dihapus!");
    }

    // ----- DELETE SUBTASK -----
    static void deleteSubtask() {
        System.out.println("\nDaftar Task Utama:");
        for (int i = 0; i < rootTasks.size(); i++) {
            System.out.println((i + 1) + ". " + rootTasks.get(i).title);
        }

        System.out.print("Pilih task utama: ");
        int idx = sc.nextInt() - 1; sc.nextLine();

        if (idx < 0 || idx >= rootTasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        Task parent = rootTasks.get(idx);

        if (parent.subtasks.isEmpty()) {
            System.out.println("Task ini tidak punya subtask.");
            return;
        }

        System.out.println("\nSubtask:");
        for (int i = 0; i < parent.subtasks.size(); i++) {
            System.out.println((i + 1) + ". " + parent.subtasks.get(i).title);
        }

        System.out.print("Pilih subtask untuk dihapus: ");
        int sidx = sc.nextInt() - 1; sc.nextLine();

        if (sidx < 0 || sidx >= parent.subtasks.size()) {
            System.out.println("Salah pilih.");
            return;
        }

        parent.subtasks.remove(sidx);
        System.out.println("Subtask berhasil dihapus!");
    }
}