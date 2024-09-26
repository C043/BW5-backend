package team7.BW5_team_7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team7.BW5_team_7.repositories.ComuneRepository;
import team7.BW5_team_7.services.ComuneService;


@Component
public class ComuneDataLoader implements CommandLineRunner {
    @Autowired
    private ComuneService comuneService;

    @Autowired
    private ComuneRepository comuneRepository;

    @Override
    public void run(String... args) throws Exception {

        if (comuneRepository.count() == 0) {
            final String CSV_FILE_CUSTOM_SEPARATOR
                    = "src/main/resources/comuni-italiani.csv";
            System.out.println("Custom Separator here semi-colon\n");
            comuneService.readDataFromCustomSeparator(CSV_FILE_CUSTOM_SEPARATOR);
            System.out.println("Comuni caricati nel database.");
        } else {
            System.out.println("I Comuni esistono già nel database, caricamento non necessario.");
        }
    }
}
