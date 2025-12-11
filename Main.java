import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Task> rootTasks = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU TO-DO LIST =====");
            System.out.println("1. Tambah Task Utama");
            System.out.println("2. Tambah Subtask");
            System.out.println("3. Tampilkan BFS Level 1");
            System.out.println("4. Tampilkan DFS Semua Task");
            System.out.println("5. Edit Task/Subtask");
            System.out.println("6. Hapus Task/Subtask");
            System.out.println("7. Statistik");
            System.out.println("8. Exit");
            System.out.print("Pilih: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addRootTask();
                case 2 -> addSubtask();
                case 3 -> showBFSTopLevel();
                case 4 -> showDFSAll();
                case 5 -> editMenu();
                case 6 -> deleteMenu();
                case 7 -> {
                    showStatistics();
                    showPieChartText(rootTasks);
                }
                case 8 -> { System.out.println("Keluar..."); return; }
                default -> System.out.println("Pilihan salah!");
            }
        }
    }

    static Status parseStatus(String s) {
        if (s == null) return Status.PENDING;
        s = s.trim().toLowerCase();
        return switch (s) {
            case "done" -> Status.DONE;
            case "in progress", "inprogress" -> Status.IN_PROGRESS;
            default -> Status.PENDING;
        };
    }

    // add task
    static void addRootTask() {
        System.out.print("Judul: ");
        String title = sc.nextLine();

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate dl = LocalDate.parse(sc.nextLine());

        System.out.print("Priority (high/medium/easy): ");
        String pr = sc.nextLine();
        if (pr.isBlank()) pr = "easy";

        System.out.print("Status (Pending/In Progress/Done): ");
        Status st = parseStatus(sc.nextLine());
        rootTasks.add(new Task(title, dl, pr, st));

        System.out.println("Task utama berhasil ditambahkan!");
    }

    static void addSubtask() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task utama.");
            return;
        }
        for (int i=0;i<rootTasks.size();i++)
            System.out.println((i+1)+". "+rootTasks.get(i).title);

            System.out.print("Pilih task utama: ");
            int idx = sc.nextInt()-1; sc.nextLine();

        if (idx<0||idx>=rootTasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        Task parent = rootTasks.get(idx);

        System.out.print("Judul subtask: ");
        String title = sc.nextLine();

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate dl = LocalDate.parse(sc.nextLine());

        System.out.print("Priority (high/medium/easy): ");
        String pr = sc.nextLine();

        if (pr.isBlank()) pr="easy";
        System.out.print("Status (Pending/In Progress/Done): ");
        Status st = parseStatus(sc.nextLine());

        parent.addSubtask(new Task(title, dl, pr, st));
        System.out.println("Subtask berhasil ditambahkan!");
    }

    // show task
    static void showBFSTopLevel() {
        System.out.println("\n=== BFS Level 1 ===");
        bubbleSort(rootTasks);
        for (Task t : rootTasks)
            System.out.println("- " + t.title + " | " + Color.coloredPriority(t.priority) + " | " +
                    t.deadline + " | " + Color.coloredStatus(t.status) + " | Progress: " + t.progress + "%");

    }

        static void showDFSAll() {
        System.out.println("\n=== DFS Semua Task ===");
        bubbleSort(rootTasks);
        for (Task t: rootTasks) dfs(t,0);
    }

    static void dfs(Task t,int depth){
            System.out.println(" ".repeat(depth*2)+"- "+t.title+" | "+Color.coloredPriority(t.priority)+" | "+t.deadline+" | "+Color.coloredStatus(t.status)+" | Progress: "+t.progress+"%");
            bubbleSort(t.subtasks);
        for (Task s:t.subtasks) dfs(s,depth+1);
    }

    // edit menu
    static void editMenu() {
        if (rootTasks.isEmpty()) {
            System.out.println("Belum ada task.");
            return;
        }
        System.out.println("1. Edit Task Utama\n2. Edit Subtask");
        int c=sc.nextInt();
        sc.nextLine();
        if(c==1)
            editRootTask();
        else if(c==2)
            editSubtask();
    }

    static void editRootTask() {
        for(int i=0;i<rootTasks.size();i++)
            System.out.println((i+1)+". "+rootTasks.get(i).title);
            System.out.print("Pilih task: ");

            int idx=sc.nextInt()-1;
            sc.nextLine();
        if(idx<0||idx>=rootTasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        Task t=rootTasks.get(idx);

        System.out.print("Judul baru: ");
        t.title=sc.nextLine();

        System.out.print("Deadline baru (YYYY-MM-DD): ");
        t.deadline=LocalDate.parse(sc.nextLine());

        System.out.print("Priority baru: ");
        String pr=sc.nextLine(); if(!pr.isBlank()) t.priority=pr;

        System.out.print("Status baru: ");
        t.status=parseStatus(sc.nextLine());

        t.updateProgress();
        System.out.println("Task berhasil diedit!");
    }

    static void editSubtask() {
        for(int i=0;i<rootTasks.size();i++)
            System.out.println((i+1)+". "+rootTasks.get(i).title);

        System.out.print("Pilih task utama: ");
        int idx=sc.nextInt()-1; sc.nextLine();

        if(idx<0||idx>=rootTasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        Task parent=rootTasks.get(idx);
        if(parent.subtasks.isEmpty()){
            System.out.println("Belum ada subtask.");
            return;
        }
        for(int i=0;i<parent.subtasks.size();i++)
            System.out.println((i+1)+". "+parent.subtasks.get(i).title);

        System.out.print("Pilih subtask: ");
        int sidx=sc.nextInt()-1; sc.nextLine();

        if(sidx<0||sidx>=parent.subtasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        Task t=parent.subtasks.get(sidx);

        System.out.print("Judul baru: ");
        t.title=sc.nextLine();

        System.out.print("Deadline baru: ");
        t.deadline=LocalDate.parse(sc.nextLine());

        System.out.print("Priority baru: ");
        String pr=sc.nextLine();
        if(!pr.isBlank()) t.priority=pr;

        System.out.print("Status baru: ");
        t.status=parseStatus(sc.nextLine());
        t.updateProgress(); parent.updateProgress();

        System.out.println("Subtask berhasil diedit!");
    }

    // delete
    static void deleteMenu() {
        if(rootTasks.isEmpty()){
            System.out.println("Belum ada task."); return;}

        System.out.println("1. Hapus Task Utama\n2. Hapus Subtask");
        int c=sc.nextInt();
        sc.nextLine();

        if(c==1)
            deleteRootTask();
        else if(c==2)
            deleteSubtask();
    }

    static void deleteRootTask(){
        for(int i=0;i<rootTasks.size();i++)
            System.out.println((i+1)+". "+rootTasks.get(i).title);

        System.out.print("Pilih task untuk dihapus: ");
        int idx=sc.nextInt()-1; sc.nextLine();

        if(idx<0||idx>=rootTasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        rootTasks.remove(idx);
        System.out.println("Task utama dihapus!");
    }

    static void deleteSubtask(){
        for(int i=0;i<rootTasks.size();i++)
            System.out.println((i+1)+". "+rootTasks.get(i).title);

        System.out.print("Pilih task utama: ");
        int idx=sc.nextInt()-1;
        sc.nextLine();

        if(idx<0||idx>=rootTasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        Task parent=rootTasks.get(idx);

        if(parent.subtasks.isEmpty()){
            System.out.println("Belum ada subtask.");
            return;
        }
        for(int i=0;i<parent.subtasks.size();i++)
            System.out.println((i+1)+". "+parent.subtasks.get(i).title);

        System.out.print("Pilih subtask untuk dihapus: ");
        int sidx=sc.nextInt()-1; sc.nextLine();
        if(sidx<0||sidx>=parent.subtasks.size()){
            System.out.println("Salah pilih");
            return;
        }
        parent.subtasks.remove(sidx);
        parent.updateProgress();
        System.out.println("Subtask dihapus!");
    }

    // statistik
    static void showStatistics() {
        int totalRoot = rootTasks.size(),
                totalSub = 0,
                totalDone = 0,
                totalAll = 0;
        List<Task> overdue = new ArrayList<>();
        Task nearest = null;
        long nearestDays = Long.MAX_VALUE;

        for (Task t : rootTasks) {
            totalAll++;
            if (t.status == Status.DONE)
                totalDone++;
            if (t.deadline.isBefore(LocalDate.now()) && t.status != Status.DONE)
                overdue.add(t);
            long diff = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), t.deadline);

            if (diff >= 0 && diff < nearestDays) {
                nearest = t;
                nearestDays = diff;
            }
            for (Task s : t.subtasks) {
                totalSub++;
                totalAll++;
                if (s.status == Status.DONE)
                    totalDone++;
                if (s.deadline.isBefore(LocalDate.now()) && s.status != Status.DONE)
                    overdue.add(s);
                long d = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), s.deadline);
                if (d >= 0 && d < nearestDays) {
                    nearest = s;
                    nearestDays = d;
                }
            }
        }
        double percent = totalAll == 0 ? 0 : (totalDone * 100.0 / totalAll);

        System.out.println("\nTotal task utama: " + totalRoot);
        System.out.println("Total subtask: " + totalSub);
        System.out.println("Total semua task: " + totalAll);
        System.out.println("Selesai: " + totalDone + " (" + (int) percent + "%) | Belum selesai: " + (100 - (int) percent) + "%");

        int filled = (int) Math.round(percent / 10);

//        String progressBar = "";
//        for (int i = 0; i < 10; i++) {
//            if (i < filled) {
//                if (percent < 40) progressBar += "\u001B[31m█\u001B[0m";    // merah
//                else if (percent < 70) progressBar += "\u001B[33m█\u001B[0m"; // kuning
//                else progressBar += "\u001B[32m█\u001B[0m";                  // hijau
//            } else {
//                progressBar += "░"; // blok kosong tetap
//            }
//        }
//
//        System.out.println("Progress: [" + progressBar + "] " + (int)percent + "%");
        int bars = 10;
        int filledBars = (int) Math.round(percent / 10.0);
        int emptyBars = bars - filledBars;

        String color;
        if (percent < 40) color = "\u001B[31m";      // merah
        else if (percent < 70) color = "\u001B[33m"; // kuning
        else color = "\u001B[32m";                  // hijau

        String progressBar =
                "[" +
                        color + "█".repeat(filledBars) + "\u001B[0m" +
                        "░".repeat(emptyBars) +
                        "] " + (int) percent + "%";

        System.out.println("Progress: " + progressBar);


        if (nearest != null)
            System.out.println("Task dekat deadline: " + nearest.title + " (" + nearestDays + " hari lagi)");
        if (!overdue.isEmpty()) {
            System.out.println("Task overdue:");
            for (Task t : overdue)
                System.out.println("- " + t.title + " | " + Color.coloredStatus(t.status));
        }
    }

            static void bubbleSort(List<Task> list){
        for(int i=0;i<list.size()-1;i++)
            for(int j=0;j<list.size()-i-1;j++)
                if(list.get(j).deadline.isAfter(list.get(j+1).deadline)){
                    Task tmp=list.get(j); list.set(j,list.get(j+1)); list.set(j+1,tmp);
                }
    }
    static void showPieChartText(List<Task> tasks){
        int done=0, inProgress=0, pending=0;

        for(Task t: tasks){
            switch(t.status){
                case DONE -> done++;
                case IN_PROGRESS -> inProgress++;
                case PENDING -> pending++;
            }
            for(Task s: t.subtasks){
                switch(s.status){
                    case DONE -> done++;
                    case IN_PROGRESS -> inProgress++;
                    case PENDING -> pending++;
                }
            }
        }

        int total = done + inProgress + pending;
        if(total==0) total=1; // agar tidak error

        System.out.println("\nPie Chart (Status Tasks):");
        System.out.println("Done       : " + "\u001B[32m" + "█".repeat(done*20/total) + "\u001B[0m");
        System.out.println("In Progress: " + "\u001B[33m" + "█".repeat(inProgress*20/total) + "\u001B[0m");
        System.out.println("Pending    : " + "\u001B[31m" + "█".repeat(pending*20/total) + "\u001B[0m");

    }
}
