import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Student {
    String ma_sv;
    String ho_ten;
    String gioi_tinh;
    double diem_python;
    double diem_java;
    double diem_tb;
    String ket_qua;

    public Student(String ma_sv, String ho_ten, String gioi_tinh, double diem_python, double diem_java) {
        this.ma_sv = ma_sv;
        this.ho_ten = ho_ten;
        this.gioi_tinh = gioi_tinh;
        this.diem_python = diem_python;
        this.diem_java = diem_java;
        this.diem_tb = (diem_java * 2 + diem_python) / 3;
        this.ket_qua = this.diem_tb >= 5 ? "Đậu" : this.diem_tb < 5 && this.gioi_tinh.equalsIgnoreCase("Nam") ? "Trượt" : "Đậu";
    }

    @Override
    public String toString() {
        return "Mã SV: " + ma_sv + ", Họ Tên: " + ho_ten + ", Giới Tính: " + gioi_tinh + ", Điểm Python: " + diem_python
                + ", Điểm Java: " + diem_java + ", Điểm TB: " + diem_tb + ", Kết Quả: " + ket_qua;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số học viên: ");
        int n = scanner.nextInt();
        ghiFileDanhSach(n);

        List<Student> students = docFileDanhSach();
        ghiFileSapXepDanhSach(students);

        System.out.print("Nhập họ tên học viên cần tìm: ");
        scanner.nextLine(); // Clear the buffer
        String ten = scanner.nextLine();
        timKiemHocVien(students, ten);

        System.out.println("\nNhững học viên đã đậu:");
        hienThiDaDau(students);

        System.out.println("\nNhững học viên đã trượt:");
        hienThiDaTruot(students);

        System.out.println("\nNhững học viên có điểm trung bình lớn hơn 8:");
        hienThiDiemTbLonHon8(students);

        scanner.close();
    }

    public static void ghiFileDanhSach(int n) {
        try (PrintWriter writer = new PrintWriter("input.txt")) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                System.out.print("Nhập mã sinh viên: ");
                String ma_sv = scanner.next();
                System.out.print("Nhập họ tên: ");
                String ho_ten = scanner.next();
                System.out.print("Nhập giới tính: ");
                String gioi_tinh = scanner.next();
                System.out.print("Nhập điểm Python: ");
                double diem_python = scanner.nextDouble();
                System.out.print("Nhập điểm Java: ");
                double diem_java = scanner.nextDouble();

                writer.println(ma_sv + "," + ho_ten + "," + gioi_tinh + "," + diem_python + "," + diem_java);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> docFileDanhSach() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(data[0], data[1], data[2], Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void ghiFileSapXepDanhSach(List<Student> students) {
        Collections.sort(students, (s1, s2) -> Double.compare(s2.diem_tb, s1.diem_tb));
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            for (Student student : students) {
                writer.println(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void timKiemHocVien(List<Student> students, String ten) {
        boolean found = false;
        for (Student student : students) {
            if (student.ho_ten.equalsIgnoreCase(ten)) {
                found = true;
                System.out.println(student);
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy học viên có tên " + ten);
        }
    }

    public static void hienThiDaDau(List<Student> students) {
        for (Student student : students) {
            if (student.ket_qua.equals("Đậu")) {
                System.out.println(student);
            }
        }
    }

    public static void hienThiDaTruot(List<Student> students) {
        for (Student student : students) {
            if (student.ket_qua.equals("Trượt")) {
                System.out.println(student);
            }
        }
    }

    public static void hienThiDiemTbLonHon8(List<Student> students) {
        for (Student student : students) {
            if (student.diem_tb > 8) {
                System.out.println(student);
            }
        }
    }
}
