public class Loker {
    private int id;
    private String perusahaan;
    private String nama;
    private String bidang;
    private int menerima;
    private String syaratKetentuan;

    
    // Constructor
    public Loker(int id, String perusahaan, String nama, String bidang, int menerima, String syaratKetentuan) {
        this.id = id;
        this.perusahaan = perusahaan;
        this.nama = nama;
        this.bidang = bidang;
        this.menerima = menerima;
        this.syaratKetentuan = syaratKetentuan;
    }


    public String getPerusahaan() {
        return perusahaan;
    }


    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }


    public String getNama() {
        return nama;
    }


    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getBidang() {
        return bidang;
    }


    public void setBidang(String bidang) {
        this.bidang = bidang;
    }


    public int getMenerima() {
        return menerima;
    }


    public void setMenerima(int menerima) {
        this.menerima = menerima;
    }


    public String getSyaratKetentuan() {
        return syaratKetentuan;
    }


    public void setSyaratKetentuan(String syaratKetentuan) {
        this.syaratKetentuan = syaratKetentuan;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
