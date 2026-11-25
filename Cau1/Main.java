import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static class SinhVien {
        private final String ma;
        private final String hoTen;
        private final String ngaySinh;

        SinhVien(String ma, String hoTen, String ngaySinh) {
            this.ma = ma.trim();
            this.hoTen = chuanHoaHoTen(hoTen);
            this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
        }

        private static String chuanHoaHoTen(String ten) {
            String[] parts = ten.trim().split("\\s+");
            StringBuilder result = new StringBuilder();
            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }
                if (result.length() > 0) {
                    result.append(' ');
                }
                result.append(Character.toUpperCase(part.charAt(0)));
                result.append(part.substring(1).toLowerCase());
            }
            return result.toString();
        }

        private static String chuanHoaNgaySinh(String ngaySinh) {
            String[] parts = ngaySinh.trim().split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return String.format("%02d/%02d/%04d", day, month, year);
        }

        @Override
        public String toString() {
            return ma + " " + hoTen + " " + ngaySinh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; i++) {
            String ma = reader.readLine();
            String hoTen = reader.readLine();
            String ngaySinh = reader.readLine();
            SinhVien sinhVien = new SinhVien(ma, hoTen, ngaySinh);
            System.out.println(sinhVien);
        }
    }
}
