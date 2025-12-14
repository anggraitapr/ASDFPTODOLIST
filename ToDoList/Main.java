package ToDoList;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU TO-DO LIST =====");
            System.out.println("1. Tambah Task Utama");
            System.out.println("2. Tambah Subtask");
            System.out.println("3. Tampilkan Task Level 1");
            System.out.println("4. Tampilkan Semua Task");
            System.out.println("5. Edit Task/Subtask");
            System.out.println("6. Hapus Task/Subtask");
            System.out.println("7. Statistik");
            System.out.println("8. Exit");
            System.out.print("Pilih: ");
            int choice = sc.nextInt();
            sc.nextLine();

            ChoiceHandler handler = new ChoiceHandler();

            switch (choice) {
                case 1 -> handler.addRootTask();
                case 2 -> handler.addSubtask();
                case 3 -> handler.showTopLevel();
                case 4 -> handler.showDFSAll();
                case 5 -> handler.editMenu();
                case 6 -> handler.deleteMenu();
                case 7 -> {
                    handler.showStatistics();
                    handler.showBarChartText();
                }
                case 8 -> {
                    System.out.println("Keluar...");
                    return;
                }
                default -> System.out.println("Pilihan salah!");
            }
        }
    }
}