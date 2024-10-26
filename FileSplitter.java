package PM4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file yang akan dibaca: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Masukkan jumlah bagian yang diinginkan: ");
        int numberOfParts = scanner.nextInt();

        Queue<String> linesQueue = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesQueue.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error membaca file: " + e.getMessage());
            return;
        }

        int totalLines = linesQueue.size();
        int partSize = (int) Math.ceil((double) totalLines / numberOfParts);

        for (int i = 0; i < numberOfParts; i++) {
            String outputFileName = "part_" + (i + 1) + ".txt";
            try (FileWriter writer = new FileWriter(outputFileName)) {
                for (int j = 0; j < partSize && !linesQueue.isEmpty(); j++) {
                    writer.write(linesQueue.poll() + System.lineSeparator());
                }
            } catch (IOException e) {
                System.err.println("Error menulis ke file: " + e.getMessage());
            }
        }

        System.out.println("File telah dipotong menjadi " + numberOfParts + " bagian.");
        scanner.close();
    }
}

