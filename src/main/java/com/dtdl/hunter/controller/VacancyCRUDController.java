package com.dtdl.hunter.controller;

import com.dtdl.hunter.entity.Vacancy;
import com.dtdl.hunter.model.VacancyDto;
import com.dtdl.hunter.model.VacancyModel;
import com.dtdl.hunter.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacancyCRUDController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping(value = "v1/createVacancy")
    public ResponseEntity<String> createVacancy(@RequestBody VacancyModel vacancyModel){

        String jobId = vacancyService.createVacancy(vacancyModel);
        return ResponseEntity.ok(jobId);
    }

    @GetMapping(value = "v1/getAllVacancies")
    public ResponseEntity<List<Vacancy>> getAllVacancies(){

        List<Vacancy> vacancies = vacancyService.getAllVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping(value = "v1/getVacancies")
    public ResponseEntity<List<VacancyDto>> getVacancies(){

        List<VacancyDto> vacancies = vacancyService.getVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping(value = "v1/deleteVacancy")
    public void deleteVacancy(@RequestParam Long id){
         vacancyService.deleteVacancy(id);

    }
}
