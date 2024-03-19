import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Đọc dữ liệu từ tệp input.txt
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Xử lý dữ liệu
                int number = Integer.parseInt(line);
                int square = number * number;

                // Ghi kết quả vào tệp outphut.txt
                BufferedWriter writer = new BufferedWriter(new FileWriter("outphut.txt", true));
                writer.write("Square of " + number + " is: " + square + "\n");
                writer.close();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
