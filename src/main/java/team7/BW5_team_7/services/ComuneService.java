package team7.BW5_team_7.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Comune;
import team7.BW5_team_7.entities.Provincia;
import team7.BW5_team_7.repositories.ComuneRepository;
import team7.BW5_team_7.repositories.ProvinciaRepository;

import java.io.FileReader;
import java.util.List;


@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void readDataFromCustomSeparator(String file) {
        try {
            FileReader filereader = new FileReader(file);

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();

//
            for (String[] row : allData) {
                if (row.length >= 3) {
                    String[] denominazione = row;

                    String provinciaStandardizzata = standardizeProvinceName(denominazione[3]);
                    Provincia found = this.provinciaRepository
                            .findByProvinciaIgnoreCase(provinciaStandardizzata);
                    if (found != null) {
                        Comune comune = new Comune(denominazione[0], denominazione[1], denominazione[2], found);

                        this.comuneRepository.save(comune);
                    } else {

                        System.out.println("Nessuna provincia trovata per: " + provinciaStandardizzata);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String standardizeProvinceName(String name) {
        return name.trim()
                .replaceAll("\\bBolzano/Bozen\\b", "Bolzano")
                .replaceAll("\\bPesaro e Urbino\\b", "Pesaro-Urbino")
                .replaceAll("\\bReggio Calabria\\b", "Reggio-Calabria")
                .replaceAll("\\bReggio nell'Emilia\\b", "Reggio-Emilia")
                .replaceAll("\\bMonza e della Brianza\\b", "Monza-Brianza")
                .replaceAll("\\bLa Spezia\\b", "La-Spezia")
                .replaceAll("\\bVibo Valentia\\b", "Vibo-Valentia")
                .replaceAll("\\bSud Sardegna\\b", "Carbonia Iglesias")
                .replaceAll("\\bAscoli Piceno\\b", "Ascoli-Piceno")
                .replaceAll("\\bForlì-Cesena\\b", "Forli-Cesena")
                .replaceAll("\\bValle d'Aosta/Vallée d'Aoste\\b", "Aosta")
                .replaceAll("\\bVerbano-Cusio-Ossola\\b", "Verbania")
                .replaceAll("\\bAscoli Piceno\\b", "Ascoli-Piceno")
                .trim();
    }

    public Comune findByName(String citta) {
        return this.comuneRepository.findByDenominazione(citta);
    }
}