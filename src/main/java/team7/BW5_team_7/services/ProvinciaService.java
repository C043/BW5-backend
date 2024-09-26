package team7.BW5_team_7.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Provincia;
import team7.BW5_team_7.repositories.ProvinciaRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional
    public void salvaProvinceDaCsv(String filePath) {
        try {

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();


            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                    .withCSVParser(parser)
                    .build();

            List<String[]> rows = reader.readAll();

            System.out.println("Righe lette: " + rows.size());

            for (int i = 1; i < rows.size(); i++) {
                String[] provinciaData = rows.get(i);

                System.out.println("Riga: " + String.join(",", provinciaData));

                if (provinciaData.length >= 3) {
                    String sigla = provinciaData[0].trim();
                    String nome = provinciaData[1].trim();
                    String regione = provinciaData[2].trim();

                    Provincia provincia = new Provincia(sigla, nome, regione);
                    provinciaRepository.save(provincia);
                } else {
                    System.out.println("Riga non valida: " + String.join(",", provinciaData));
                }
            }

            System.out.println("Le province sono state salvate nel database.");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
