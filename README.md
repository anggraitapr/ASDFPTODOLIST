## Final Project ASD (Algoritma Struktur Data) Semester Gasal 2025/2026: TO-DO LIST (TASK MANAGEMENT)

## Informasi Poject
Dosen Pengampu: Renny Pradina Kusumawardani
Kelas: D
Kelompok: 10
| No | Nama                | NRP     |
| -- | ------------------- | ------- |
| 1  | Anggraita Prabawati | 5026241103 |
| 2  | Widda Farrah Kayla  | 5026241119 |
| 3  | Milawati            | 5026241163 |
| 4  | Elfa Setiana        | 5026241208 |

## Latar Belakang
Pengelolaan tugas yang terstruktur menjadi kebutuhan penting agar berbagai pekerjaan dapat diselesaikan secara efektif dan tepat waktu. Dalam aktivitas akademik maupun sehari-hari, sering kali satu tugas utama memiliki beberapa subtask dengan tingkat prioritas dan batas waktu yang berbeda, sehingga diperlukan sistem yang mampu mengorganisasi tugas secara jelas dan sistematis.
TO-DO LIST digunakan sebagai sistem pencatatan tugas yang membantu pengguna mengelola task dan subtask, menentukan prioritas berdasarkan tingkatan level, serta menampilkan deadline terdekat. Dengan penerapan struktur data dan algoritma seperti Tree, Breadth First Search (BFS), Depth First Search (DFS), dan Sorting, sistem ini mampu menampilkan hubungan hierarki tugas secara jelas, menyusun prioritas secara sistematis, serta menampilkan tugas berdasarkan deadline terdekat sehingga pengelolaan tugas dapat dilakukan secara lebih optimal dan terencana.

## Solusi yang Ditawarkan
Solusi yang ditawarkan adalah sebuah program To-Do List yang menggunakan struktur data Tree untuk menampilkan hubungan antara task dan subtask secara terstruktur. Penerapan algoritma DFS untuk menampilkan detail task dan subtask, serta algoritma Sorting untuk mengurutkan task berdasarkan deadline terdekat. Dengan solusi ini, pengguna dapat lebih mudah menentukan prioritas dan mengelola tugas secara efektif.

## Fitur Program
Pengelolaan task dan subtask dalam bentuk struktur hierarki.
Menampilkan task utama dan subtask DFS.
Menampilkan daftar task berdasarkan deadline terdekat menggunakan sorting.
Menu statistika untuk menampilkan progress penyelesaian to-do list.
Fitur untuk menambahkan, menghapus, dan mengedit task serta sub-task

## Algoritma dan Struktur Data yang Digunakan
1. Algoritma
Bubble Sort (Deadline): Mengurutkan task dan subtask berdasarkan deadline terdekat. Pada program ini, algoritma Bubble Sort diterapkan agar task dengan batas waktu paling mendesak ditampilkan lebih awal, sehingga membantu pengguna dalam menentukan prioritas penyelesaian tugas.
DFS (Menampilkan semua task): Menampilkan seluruh struktur task beserta subtask secara mendalam. DFS memungkinkan sistem menelusuri setiap task hingga ke level terdalam, sehingga pengguna dapat melihat detail dari setiap task dan subtask yang dimiliki.

2. Struktur Data
Tree (Task & Subtask): Menyusun data tugas dalam bentuk hubungan bertingkat, di mana setiap task dapat memiliki subtask. Pengelompokan tugas secara terstruktur serta mendukung proses penelusuran data menggunakan algoritma DFS secara efisien.
List: Sistem To-Do List menggunakan struktur data List dengan implementasi ArrayList untuk menyimpan subtask. Struktur ini bersifat dinamis, berurutan, dan memudahkan proses traversal menggunakan algoritma DFS secara rekursif.

## Bukti Tampilan Program (Code)


## Riwayat Update Program
Berikut adalah riwayat update yang kami lakukan. Seluruh riwayat update di bawah ini tidak memiliki perubahan tampilan pada output yang dihasilkan

    Update 10/12/2025
1. Mengubah implementasi BFS menjadi menggunakan struktur data Queue (sekarang tidak menggunakan BFS)
2. Mengubah implementasi DFS menjadi menggunakan struktur data Stack (sekarang diubah menjadi DFS rekursif)

    Update 11/12/2025
1. DFS menggunakan stack diubah menjadi penggunaan DFS rekursif
2. Untuk menampilkan task utama ternyata tidak perlu menggunakan BFS. Oleh karena itu, proyek kami pada akhirnya tidak menggunakan algoritma BFS
   
    Update 12/12/2025
1. Class CoiceHandler dibuat untuk memisahkan code method setiap menu dari Class Main
2. Perbaikan code Bubble Sort
