package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team7.BW5_team_7.services.ComuneService;
import team7.BW5_team_7.services.ProvinciaService;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private ComuneService comuneService;

    // Endpoint per leggere e salvare le province dal CSV
    @PostMapping("/province")
    public String importaProvince(@RequestParam("filePath") String filePath) {
        provinciaService.salvaProvinceDaCsv(filePath);
        return "Importazione completata.";
    }

    @PostMapping("/comuni")
    public String importaComuni(@RequestParam("filePath") String filePath) {
        comuneService.salvaComuniDaCsv(filePath);
        return "Importazione comuni completata.";
    }
}
