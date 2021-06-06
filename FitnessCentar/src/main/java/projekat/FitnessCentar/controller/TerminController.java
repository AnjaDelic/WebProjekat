package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projekat.FitnessCentar.entity.Sala;
import projekat.FitnessCentar.entity.Termin;
import projekat.FitnessCentar.entity.Trener;
import projekat.FitnessCentar.service.TerminService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api/termin")
public class TerminController {

    @Autowired
    private TerminService terminService;



}
