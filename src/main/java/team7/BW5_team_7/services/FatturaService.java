package team7.BW5_team_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.repositories.FatturaRepository;

import java.util.List;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;



