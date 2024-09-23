package team7.BW5_team_7.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.BW5_team_7.services.IndirizzoService;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    private IndirizzoService indirizzoService;
    
}
