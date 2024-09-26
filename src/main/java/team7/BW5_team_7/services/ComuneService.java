package team7.BW5_team_7.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Comune;
import team7.BW5_team_7.entities.Provincia;
import team7.BW5_team_7.repositories.ComuneRepository;
import team7.BW5_team_7.repositories.ProvinciaRepository;


import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    // Funzione per normalizzare i nomi rimuovendo accenti e apostrofi
    private String normalizzaNome(String nome) {
        String normalized = Normalizer.normalize(nome, Normalizer.Form.NFD);
        // Rimuoviamo i diacritici (accenti) e altri simboli speciali
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replace("'", "");
    }

    @Transactional
    public void salvaComuniDaCsv(String filePath) {
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')  // Usa il punto e virgola come delimitatore
                    .build();

            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                    .withCSVParser(parser)
                    .build();

            List<String[]> rows = reader.readAll();
            System.out.println("Righe lette: " + rows.size());

            // Mappa delle province con nomi alternativi
            Map<String, String> mappaProvinceSpeciali = new HashMap<>();
            mappaProvinceSpeciali.put("sud sardegna", "Sardegna");
            mappaProvinceSpeciali.put("Sud Sardegna", "Sardegna");
            mappaProvinceSpeciali.put("oristano", "Oristano");

            // Ignoriamo la prima riga (intestazione)
            for (int i = 1; i < rows.size(); i++) {
                String[] comuneData = rows.get(i);

                // Assicuriamoci che ci siano almeno 4 colonne
                if (comuneData.length >= 4) {
                    String codiceProvinciaStorico = comuneData[0].trim();  // Codice Provincia
                    String progressivoComune = comuneData[1].trim();  // Progressivo del Comune
                    String nomeComune = comuneData[2].trim();  // Nome del Comune
                    String nomeProvincia = comuneData[3].trim();  // Nome della Provincia

                    // Gestione di #RIF!
                    if (codiceProvinciaStorico.equals("#RIF!")) {
                        codiceProvinciaStorico = "000";  // Valore predefinito per codici non validi
                    }

                    // Normalizza il nome del comune e della provincia (rimuovi accenti e apostrofi)
                    nomeComune = normalizzaNome(nomeComune);
                    nomeProvincia = normalizzaNome(nomeProvincia);

                    // Controlliamo se la provincia ha un nome speciale
                    if (mappaProvinceSpeciali.containsKey(nomeProvincia)) {
                        nomeProvincia = mappaProvinceSpeciali.get(nomeProvincia);
                    }

                    // Log per verificare i dati letti
                    System.out.println("Codice Provincia Storico: " + codiceProvinciaStorico + ", Nome Comune: " + nomeComune + ", Nome Provincia: " + nomeProvincia);

                    // Trova la provincia corrispondente ignorando maiuscole e minuscole
                    Optional<Provincia> provinciaOpt = provinciaRepository.findByNomeIgnoreCase(nomeProvincia);
                    if (provinciaOpt.isPresent()) {
                        Provincia provincia = provinciaOpt.get();
                        Comune comune = new Comune(codiceProvinciaStorico, nomeComune, provincia);
                        comuneRepository.save(comune);
                    } else {
                        System.out.println("Provincia non trovata per il comune: " + nomeComune + ", Nome Provincia: " + nomeProvincia);
                    }
                } else {
                    System.out.println("Riga con colonne insufficienti: " + String.join(";", comuneData));
                }
            }

            System.out.println("I comuni sono stati salvati nel database.");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}

