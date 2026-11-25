import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    interface MienGiam {
        long tinhGiam(long hocPhi);
    }

    static class ChinhSachCon implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return hocPhi / 2;
        }
    }

    static class ChinhSachHoNgheo implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return hocPhi * 70 / 100;
        }
    }

    static class ChinhSachHocBong implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return Math.min(3000000L, hocPhi);
        }
    }

    static abstract class SinhVien {
        protected final String maSinhVien;
        protected final String maHe;
        protected final int soTinChi;
        protected MienGiam mienGiam;

        SinhVien(String maSinhVien, String maHe, int soTinChi) {
            this.maSinhVien = maSinhVien;
            this.maHe = maHe;
            this.soTinChi = soTinChi;
            this.mienGiam = null;
        }

        abstract long hocPhi();

        long soTienGiam() {
            return mienGiam == null ? 0 : mienGiam.tinhGiam(hocPhi());
        }

        long phaiNop() {
            return hocPhi() - soTienGiam();
        }
    }

    static class SinhVienChinhQuy extends SinhVien {
        SinhVienChinhQuy(String maSinhVien, int soTinChi) {
            super(maSinhVien, "CQ", soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L;
        }
    }

    static class SinhVienChatLuongCao extends SinhVien {
        SinhVienChatLuongCao(String maSinhVien, int soTinChi) {
            super(maSinhVien, "CLC", soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L * 3 / 2;
        }
    }

    static class SinhVienLienThong extends SinhVien {
        SinhVienLienThong(String maSinhVien, int soTinChi) {
            super(maSinhVien, "LT", soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L + 2000000L;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        ArrayList<SinhVien> danhSach = new ArrayList<>();
        HashMap<String, SinhVien> theoMa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String maHe = parts[0];
            String maSinhVien = parts[1];
            int soTinChi = Integer.parseInt(parts[2]);

            SinhVien sinhVien;
            if ("CQ".equals(maHe)) {
                sinhVien = new SinhVienChinhQuy(maSinhVien, soTinChi);
            } else if ("CLC".equals(maHe)) {
                sinhVien = new SinhVienChatLuongCao(maSinhVien, soTinChi);
            } else {
                sinhVien = new SinhVienLienThong(maSinhVien, soTinChi);
            }
            danhSach.add(sinhVien);
            theoMa.put(maSinhVien, sinhVien);
        }

        int m = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < m; i++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String maSinhVien = parts[0];
            String maChinhSach = parts[1];
            SinhVien sinhVien = theoMa.get(maSinhVien);
            if (sinhVien == null) {
                System.out.println("NOT FOUND " + maSinhVien);
            } else if ("CS".equals(maChinhSach)) {
                sinhVien.mienGiam = new ChinhSachCon();
            } else if ("HN".equals(maChinhSach)) {
                sinhVien.mienGiam = new ChinhSachHoNgheo();
            } else if ("HB".equals(maChinhSach)) {
                sinhVien.mienGiam = new ChinhSachHocBong();
            }
        }

        LinkedHashMap<String, Integer> soSinhVien = new LinkedHashMap<>();
        LinkedHashMap<String, Long> tongPhaiNop = new LinkedHashMap<>();
        for (String maHe : new String[]{"CQ", "CLC", "LT"}) {
            soSinhVien.put(maHe, 0);
            tongPhaiNop.put(maHe, 0L);
        }

        for (SinhVien sinhVien : danhSach) {
            long hocPhi = sinhVien.hocPhi();
            long soTienGiam = sinhVien.soTienGiam();
            long phaiNop = sinhVien.phaiNop();
            System.out.println(sinhVien.maSinhVien + " " + sinhVien.maHe + " "
                    + hocPhi + " " + soTienGiam + " " + phaiNop);

            soSinhVien.put(sinhVien.maHe, soSinhVien.get(sinhVien.maHe) + 1);
            tongPhaiNop.put(sinhVien.maHe,
                    tongPhaiNop.get(sinhVien.maHe) + phaiNop);
        }

        for (String maHe : new String[]{"CQ", "CLC", "LT"}) {
            if (soSinhVien.get(maHe) > 0) {
                System.out.println(maHe + " " + soSinhVien.get(maHe)
                        + " " + tongPhaiNop.get(maHe));
            }
        }
    }
}
