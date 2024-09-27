package team7.BW5_team_7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team7.BW5_team_7.repositories.ProvinciaRepository;
import team7.BW5_team_7.services.ProvinciaService;

@Component
public class ProvinciaDataLoader implements CommandLineRunner {
    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (provinciaRepository.count() == 0) {
            final String CSV_FILE_CUSTOM_SEPARATOR
                    = "src/main/resources/province-italiane.csv";
            System.out.println("Custom Separator here semi-colon\n");
            provinciaService.readDataFRomCustomSeparator(CSV_FILE_CUSTOM_SEPARATOR);
            System.out.println("Province caricati nel database.");
        } else {
            System.out.println("Le Provincie esistono già nel database, caricamento non necessario.");
        }
    }


}
