public class User {
    private String username;
    private String password;
    
    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}

class Admin extends User {
    private String role;
    
    // Constructor
    public Admin(String username, String password, String role) {
        super(username, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}

class Mahasiswa extends User {
    private int id;
    private String nim;
    private double ipk;
    private String judulTA;
    private int tahunLulus;
    private int lamaKerjaSetelahLulus;
    private String pekerjaanSekarang;
    private String kesesuaian;
    
    // Constructor
    public Mahasiswa(int id, String username, String judulTA, String password, String nim, double ipk, int tahunLulus, int lamaKerjaSetelahLulus, String pekerjaanSekarang, String kesesuaian) {
        super(username, password);
        this.id = id;
        this.nim = nim;
        this.ipk = ipk;
        this.judulTA = judulTA;
        this.tahunLulus = tahunLulus;
        this.lamaKerjaSetelahLulus = lamaKerjaSetelahLulus;
        this.pekerjaanSekarang = pekerjaanSekarang;
        this.kesesuaian = kesesuaian;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    public int getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(int tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    public int getLamaKerjaSetelahLulus() {
        return lamaKerjaSetelahLulus;
    }

    public void setLamaKerjaSetelahLulus(int lamaKerjaSetelahLulus) {
        this.lamaKerjaSetelahLulus = lamaKerjaSetelahLulus;
    }

    public String getPekerjaanSekarang() {
        return pekerjaanSekarang;
    }

    public void setPekerjaanSekarang(String pekerjaanSekarang) {
        this.pekerjaanSekarang = pekerjaanSekarang;
    }

    public String getJudulTA() {
        return judulTA;
    }

    public void setJudulTA(String judulTA) {
        this.judulTA = judulTA;
    }

    public String getKesesuaian() {
        return kesesuaian;
    }

    public void setKesesuaian(String kesesuaian) {
        this.kesesuaian = kesesuaian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
