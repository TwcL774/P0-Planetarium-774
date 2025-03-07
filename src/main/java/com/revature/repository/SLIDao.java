package com.revature.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.SLI;

public class SLIDao {

    public SLI getSLI() {
        try {
            generateSLI();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("logs/sliSummary"));
            List<String> fromFile = new ArrayList<>();
            String lineRead = bufferedReader.readLine();

            while (lineRead != null) {
                fromFile.add(lineRead);
                lineRead = bufferedReader.readLine();
            }

            bufferedReader.close();
            return new SLI(
                    fromFile.get(0), fromFile.get(1),
                    fromFile.get(2), fromFile.get(3),
                    fromFile.get(4), fromFile.get(6), fromFile.get(7));
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return new SLI();
        }
    }

    public void generateSLI() throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "logs/SLI.sh");
        Process process = processBuilder.start();
        process.waitFor();
    }
}
