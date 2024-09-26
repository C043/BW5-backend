package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team7.BW5_team_7.services.ProvinciaService;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    // Endpoint per leggere e salvare le province dal CSV
    @PostMapping("/import")
    public String importaProvince(@RequestParam("filePath") String filePath) {
        provinciaService.salvaProvinceDaCsv(filePath);
        return "Importazione completata.";
    }
}
