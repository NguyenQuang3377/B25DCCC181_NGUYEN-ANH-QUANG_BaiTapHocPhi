import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static abstract class SinhVien {
        protected final String maSinhVien;
        protected final int soTinChi;

        SinhVien(String maSinhVien, int soTinChi) {
            this.maSinhVien = maSinhVien;
            this.soTinChi = soTinChi;
        }

        abstract long hocPhi();
    }

    static class SinhVienChinhQuy extends SinhVien {
        SinhVienChinhQuy(String maSinhVien, int soTinChi) {
            super(maSinhVien, soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L;
        }
    }

    static class SinhVienChatLuongCao extends SinhVien {
        SinhVienChatLuongCao(String maSinhVien, int soTinChi) {
            super(maSinhVien, soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L * 3 / 2;
        }
    }

    static class SinhVienLienThong extends SinhVien {
        SinhVienLienThong(String maSinhVien, int soTinChi) {
            super(maSinhVien, soTinChi);
        }

        @Override
        long hocPhi() {
            return (long) soTinChi * 450000L + 2000000L;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());

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
            System.out.println(maSinhVien + " " + maHe + " " + sinhVien.hocPhi());
        }
    }
}
