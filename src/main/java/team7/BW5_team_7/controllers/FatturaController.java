package team7.BW5_team_7.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.BW5_team_7.services.FatturaService;

@RestController
@RequestMapping("/fattura")


public class FatturaController {
    @Autowired
    private FatturaService fatturaService;
}
