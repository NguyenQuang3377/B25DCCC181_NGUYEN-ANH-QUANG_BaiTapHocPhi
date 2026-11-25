import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Comparator;

public class Main {
    static class DuLieuKhongHopLeException extends Exception {
        DuLieuKhongHopLeException(String message) {
            super(message);
        }
    }

    static abstract class SinhVien {
        protected final String maSinhVien;
        protected final String maHe;
        protected final int soTinChi;

        SinhVien(String maSinhVien, String maHe, int soTinChi) {
            this.maSinhVien = maSinhVien;
            this.maHe = maHe;
            this.soTinChi = soTinChi;
        }

        abstract long hocPhi();

        @Override
        public String toString() {
            return maSinhVien + " " + maHe + " " + hocPhi();
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

    static class QuanLyHocPhi {
        private final ArrayList<SinhVien> danhSach = new ArrayList<>();
        private final HashSet<String> maDaNhap = new HashSet<>();

        SinhVien themSinhVien(String maHe, String maSinhVien, String tinChi)
                throws DuLieuKhongHopLeException {
            if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
                throw new DuLieuKhongHopLeException("INVALID " + maSinhVien);
            }

            int soTinChi;
            try {
                soTinChi = Integer.parseInt(tinChi);
            } catch (NumberFormatException e) {
                throw new DuLieuKhongHopLeException("INVALID " + maSinhVien);
            }

            if (soTinChi < 1 || soTinChi > 30) {
                throw new DuLieuKhongHopLeException("INVALID " + maSinhVien);
            }
            if (maDaNhap.contains(maSinhVien)) {
                throw new DuLieuKhongHopLeException("INVALID " + maSinhVien);
            }

            SinhVien sinhVien;
            if (maHe.equals("CQ")) {
                sinhVien = new SinhVienChinhQuy(maSinhVien, soTinChi);
            } else if (maHe.equals("CLC")) {
                sinhVien = new SinhVienChatLuongCao(maSinhVien, soTinChi);
            } else {
                sinhVien = new SinhVienLienThong(maSinhVien, soTinChi);
            }
            danhSach.add(sinhVien);
            maDaNhap.add(maSinhVien);
            return sinhVien;
        }

        ArrayList<SinhVien> getDanhSachSapXep() {
            ArrayList<SinhVien> ketQua = new ArrayList<>(danhSach);
            ketQua.sort(Comparator
                    .comparingLong((SinhVien sinhVien) -> sinhVien.hocPhi()).reversed()
                    .thenComparing(sinhVien -> sinhVien.maSinhVien));
            return ketQua;
        }

        long tongHocPhi() {
            long tong = 0;
            for (SinhVien sinhVien : danhSach) {
                tong += sinhVien.hocPhi();
            }
            return tong;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        QuanLyHocPhi quanLy = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String maSinhVien = parts.length >= 2 ? parts[1] : "";
            try {
                if (parts.length != 3) {
                    throw new DuLieuKhongHopLeException("INVALID " + maSinhVien);
                }
                quanLy.themSinhVien(parts[0], parts[1], parts[2]);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println(e.getMessage());
            }
        }

        for (SinhVien sinhVien : quanLy.getDanhSachSapXep()) {
            System.out.println(sinhVien);
        }
        System.out.println("TONG " + quanLy.tongHocPhi());
    }
}
