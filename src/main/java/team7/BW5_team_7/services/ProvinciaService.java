package team7.BW5_team_7.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Provincia;
import team7.BW5_team_7.repositories.ProvinciaRepository;

import java.io.FileReader;
import java.util.Comparator;
import java.util.List;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void readDataFRomCustomSeparator(String file) {
        try {
            FileReader fileReader = new FileReader(file);

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            allData.sort(Comparator.comparing(row -> row[1].trim().toLowerCase()));

            for (String[] row : allData) {
                Provincia pr = new Provincia(row[0], row[1], row[2]);
                this.provinciaRepository.save(pr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
