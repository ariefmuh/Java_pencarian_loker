import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class app {

	private static Connection connection;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            connection = DatabaseConnector.connect();

            while (true) {
                // Display main menu options
                displayMainMenu();

                int choice = sc.nextInt();
                if (choice == 0) {
                    adminLoginMenu(sc);
                } else if (choice == 1) {
                    mahasiswaLoginMenu(sc);
                } else if (choice == 2) {
                    createNewMahasiswa(sc);
                } else {
                    System.out.println("Menghentikan Program");
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
            if (sc != null) {
                sc.close();
            }
        }
    }
    
    // Method Untuk menampilkan Tampilan Utama
    public static void displayMainMenu() {
        System.out.println("Masukkan Angka untuk");
        System.out.println("1. Login sebagai Mahasiswa");
        System.out.println("2. Register");
        System.out.println("Lainnya. Stop Program");
    }
    
    // Tampilan login untuk admin
    public static void adminLoginMenu(Scanner sc) throws SQLException {
        // Admin login process
        System.out.println("Masukkan username Admin: ");
        String adminUsername = sc.next();
        System.out.println("Masukkan password Admin: ");
        String adminPassword = sc.next();

        if (adminLogin(adminUsername, adminPassword)) {
            adminActionsMenu(sc);
        } else {
            System.out.println("Login Admin gagal!");
        }
    }

    // Method untuk aksi admin ketika login
    public static void adminActionsMenu(Scanner sc) throws SQLException {
        while (true) {
            // Display admin actions menu
            displayAdminActionsMenu();

            int adminChoice = sc.nextInt();
            if (adminChoice == 1) {
                showMahasiswa("ALL");
            } else if (adminChoice == 2) {
                showLoker();
            } else if (adminChoice == 3) {
                createNewMahasiswa(sc);
            } else if (adminChoice == 4) {
                createNewLoker(sc);
            } else if (adminChoice == 5) {
                showMahasiswa("ALL");
                System.out.println("Silahkan Pilih Data Mahasiswa yang ingin dihapus (id):");
                deleteMahasiswa(sc);
            } else if (adminChoice == 6) {
                showLoker();
                System.out.println("Silahkan Pilih Data Loker yang ingin dihapus (id)");
                deleteLoker(sc);
            } else {
                System.out.println("Logging Out...");
                break;
            }
        }
    }
    
    // Method Untuk Menampilkan actions menu admin
    public static void displayAdminActionsMenu() {
        System.out.println("Login Admin berhasil!");
        System.out.println("Masukkan Angka untuk");
        System.out.println("1. Tunjukkan Daftar Mahasiswa");
        System.out.println("2. Tunjukkan Daftar Loker");
        System.out.println("3. Input Mahasiswa Baru");
        System.out.println("4. Input Loker Baru");
        System.out.println("5. Hapus Mahasiswa");
        System.out.println("6. Hapus Loker");
        System.out.println("Lainnya untuk logout");
    }

    // Menu untuk login mahasiswa
    public static void mahasiswaLoginMenu(Scanner sc) throws SQLException {
        // Mahasiswa login process
        System.out.println("Masukkan username Mahasiswa: ");
        String mahasiswaUsername = sc.next();
        System.out.println("Masukkan password Mahasiswa: ");
        String mahasiswaPassword = sc.next();

        Mahasiswa mahasiswa = mahasiswaLogin(mahasiswaUsername, mahasiswaPassword);
        if (mahasiswa != null) {
            mahasiswaActionsMenu(sc, mahasiswa);
        } else {
            System.out.println("Login Mahasiswa gagal!");
        }
    }

    // Menu untuk sesudah Mahasiswa login
    public static void mahasiswaActionsMenu(Scanner sc, Mahasiswa mahasiswa) throws SQLException {
        while (true) {
            // Menampilkan Mahasiswa actions menu
            displayMahasiswaActionsMenu();

            int mahasiswaChoice = sc.nextInt();
            if (mahasiswaChoice == 1) {
                showLoker();
            } else if (mahasiswaChoice == 2) {
                showMahasiswa(String.valueOf(mahasiswa.getId()));
            } else {
                System.out.println("Logging Out");
                break;
            }
        }
    }

    // Method Untuk Menampilkan menu aksi mahasiswa
    public static void displayMahasiswaActionsMenu() {
        System.out.println("Login Mahasiswa berhasil!");
        System.out.println("Masukkan Angka untuk");
        System.out.println("1. Tunjukkan Daftar Loker Yang ada");
        System.out.println("2. Tunjukkan Data anda");
        System.out.println("Lainnya untuk logout");
    }

    // Method untuk memunculkan daftar mahasiswa dimana data berupa id jika ingin menampilkan 1 mahasiswa saja jika tidak maka data = "ALL"
    public static void showMahasiswa(String data) throws SQLException {
        List<Mahasiswa> mahasiswas = getMahasiswaData(data);

        final int COLUMN_WIDTH = 15;
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "id", "Nama", "NIM", "IPK", "TahunLulus", "LamaKerja", "Pekerjaan", "Kesesuaian", "JudulTA");

        for (int i = 0; i < mahasiswas.size(); i++) {
            String formattedid = String.format("%-" + COLUMN_WIDTH + "s", i+1);
            String formattedNama = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getUsername());
            String formattedNim = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getNim());
            String formattedIpk = String.format("%-" + COLUMN_WIDTH + ".2f", mahasiswas.get(i).getIpk());
            String formattedTahunLulus = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getTahunLulus());
            String formattedLamaKerja = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getLamaKerjaSetelahLulus());
            String formattedPekerjaan = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getPekerjaanSekarang());
            String formattedKesesuaian = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getKesesuaian());
            String formattedJudulTA = String.format("%-" + COLUMN_WIDTH + "s", mahasiswas.get(i).getJudulTA());

            System.out.printf("%s %s %s %s %s %s %s %s %s\n", formattedid, formattedNama, formattedNim, formattedIpk, formattedTahunLulus, formattedLamaKerja, formattedPekerjaan, formattedKesesuaian, formattedJudulTA);
        }
    }

    // Method untuk menampilkan semua loker yang ada
    public static void showLoker() throws SQLException {
        List<Loker> lokers = getLokerData();
        final int COLUMN_WIDTH = 18;

        System.out.printf("%-18s %-18s %-18s %-18s %-18s\n", "ID", "Nama", "Bidang", "Perusahaan", "Syarat Dan Ketentuan");
        for (int i = 0; i < lokers.size(); i++) {
            String formattedid = String.format("%-" + COLUMN_WIDTH + "s", i+1);
            String formattedNama = String.format("%-" + COLUMN_WIDTH + "s", lokers.get(i).getNama());
            String formattedBidang = String.format("%-" + COLUMN_WIDTH + "s", lokers.get(i).getBidang());
            String formattedPerusahaan = String.format("%-" + COLUMN_WIDTH + "s", lokers.get(i).getPerusahaan());
            String formattedSyaratKetentuan = String.format("%-" + COLUMN_WIDTH + "s", lokers.get(i).getSyaratKetentuan());

            System.out.printf("%s %s %s %s %s\n", formattedid, formattedNama, formattedBidang, formattedPerusahaan, formattedSyaratKetentuan);
        }
    }

    // Method untuk mengambil data admin menggunakan username dan password
    public static boolean adminLogin(String username, String password) throws SQLException {
        List<Admin> admins = getAdminData();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method untuk login mahasiswa
    public static Mahasiswa mahasiswaLogin(String username, String password) throws SQLException {
        List<Mahasiswa> mahasiswas = getMahasiswaData("ALL");
        for (Mahasiswa mahasiswa : mahasiswas) {
            if (mahasiswa.getUsername().equals(username) && mahasiswa.getPassword().equals(password)) {
                return mahasiswa;
            }
        }
        return null;
    }

    // Method untuk mengambil semua data pada tabel admin
    public static List<Admin> getAdminData() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet adminResultSet = statement.executeQuery("SELECT * FROM admin");
            while (adminResultSet.next()) {
                String username = adminResultSet.getString("username");
                String password = adminResultSet.getString("password");
                String role = adminResultSet.getString("role");

                Admin admin = new Admin(username, password, role);
                admins.add(admin);
            }
        }
        return admins;
    }

    // Method untuk mengambil data mahasiswa sesuai data yang diberikan jika 'ALL' maka semua data dan jika berupa id maka data spesifik mahasiswa  
    public static List<Mahasiswa> getMahasiswaData(String data) throws SQLException {
        List<Mahasiswa> mahasiswas = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet mahasiswaResultSet;
            if (data.equals("ALL")) {
                mahasiswaResultSet = statement.executeQuery("SELECT * FROM mahasiswa");
            } else {    
                mahasiswaResultSet = statement.executeQuery("SELECT * FROM mahasiswa WHERE id='" + data + "'");
            }
            while (mahasiswaResultSet.next()) {
                int id = mahasiswaResultSet.getInt("id");
                String username = mahasiswaResultSet.getString("Nama");
                String password = mahasiswaResultSet.getString("Password");
                String nim = mahasiswaResultSet.getString("Nim");
                String judulTA = mahasiswaResultSet.getString("JudulTA");
                double ipk = mahasiswaResultSet.getDouble("IPK");
                int tahunLulus = mahasiswaResultSet.getInt("TahunLulus");
                int lamaKerja = mahasiswaResultSet.getInt("LamaKerja");
                String kesesuaian = mahasiswaResultSet.getString("Kesesuaian");
                String pekerjaan = mahasiswaResultSet.getString("Pekerjaan");

                Mahasiswa mahasiswa = new Mahasiswa(id, username, judulTA, password, nim, ipk, tahunLulus, lamaKerja, pekerjaan, kesesuaian);
                mahasiswas.add(mahasiswa);
            }
        }
        return mahasiswas;
    }

    // Method untuk mengambil data loker
    public static List<Loker> getLokerData() throws SQLException {
        List<Loker> lokers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet lokerResultSet = statement.executeQuery("SELECT * FROM loker");
            while (lokerResultSet.next()) {
                int id = lokerResultSet.getInt("id");
                String perusahaan = lokerResultSet.getString("Perusahaan");
                String nama = lokerResultSet.getString("Nama");
                String bidang = lokerResultSet.getString("Bidang");
                int menerima = lokerResultSet.getInt("Menerima");
                String syaratKetentuan = lokerResultSet.getString("SyaratKetentuan");

                Loker loker = new Loker(id, perusahaan, nama, bidang, menerima, syaratKetentuan);
                lokers.add(loker);
            }
        }
        return lokers;
    }

    // Method Untuk Menghapus mahasiswa
    public static void deleteMahasiswa(Scanner sc) throws SQLException {
        int id = sc.nextInt();
        id = id - 1;
        List<Mahasiswa> mahasiswa = getMahasiswaData("ALL");
        int deleted = mahasiswa.get(id).getId();
        String sql = "DELETE FROM `mahasiswa` WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deleted);

        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Data deleted successfully!");
        } else {
            System.out.println("No data found for the given ID.");
        }
    }
    public static void deleteLoker(Scanner sc) throws SQLException {
        int id = sc.nextInt();
        id = id - 1;
        List<Loker> Lokers = getLokerData();
        int deleted = Lokers.get(id).getId();
        String sql = "DELETE FROM `loker` WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deleted);

        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Data deleted successfully!");
        } else {
            System.out.println("No data found for the given ID.");
        }
    }
    // Method untuk registrasi dan membuat mahasiswa pada aksi di admin untunk membuat mahasiswa baru
    public static void createNewMahasiswa(Scanner sc) throws SQLException {
        System.out.println("Masukkan data informasi anda: ");
        System.out.println("Nama: ");
        sc.nextLine();
        String nama = sc.nextLine();
        System.out.println("NIM: ");
        String nim = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Judul Tugas Akhir: ");
        String judulTA = sc.nextLine();
        System.out.println("IPK: ");
        float ipk = sc.nextFloat();
        System.out.println("Tahun Lulus: ");
        int tahunLulus = sc.nextInt();
        System.out.println("Lama Kerja Setelah Anda Lulus (Tahun): ");
        int lamaKerja = sc.nextInt();
        sc.nextLine();
        System.out.println("Pekerjaan Anda: ");
        String pekerjaan = sc.nextLine();
        System.out.println("Kesesuaian Pekerjaan Anda Sesuai Dengan Prodi Yang Anda Ambil (Sesuai/Tidak Sesuai): ");
        String kesesuaian = sc.nextLine();

        // Perform the insertion into the database
        String sql = "INSERT INTO `mahasiswa` (`Nama`, `Nim`, `Password`, `JudulTA`, `IPK`, `TahunLulus`, `LamaKerja`, `Pekerjaan`, `Kesesuaian`) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nama);
            statement.setString(2, nim);
            statement.setString(3, password);
            statement.setString(4, judulTA);
            statement.setFloat(5, ipk);
            statement.setInt(6, tahunLulus);
            statement.setInt(7, lamaKerja);
            statement.setString(8, pekerjaan);
            statement.setString(9, kesesuaian);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data Mahasiswa berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan data Mahasiswa!");
            }
        }
    }

    public static void createNewLoker(Scanner sc) throws SQLException {
        System.out.println("Masukkan informasi loker yang ingin dimasukkan: ");
        System.out.println("Nama Pekerjaan: ");
        sc.nextLine();
        String nama = sc.nextLine();
        System.out.println("Bidang: ");
        String bidang = sc.nextLine();
        System.out.println("Perusahaan: ");
        String perusahaan = sc.nextLine();
        System.out.println("Syarat dan Ketentuan: ");
        String syaratKetentuan = sc.nextLine();
        System.out.println("Jumlah menerima: ");
        int menerima = sc.nextInt();

        // Perform the insertion into the database
        String sql = "INSERT INTO `loker` (`Perusahaan`, `Nama`, `Bidang`, `Menerima`, `SyaratKetentuan`) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, perusahaan);
            statement.setString(2, nama);
            statement.setString(3, bidang);
            statement.setInt(4, menerima);
            statement.setString(5, syaratKetentuan);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data Mahasiswa berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan data Mahasiswa!");
            }
        }
    }

}

