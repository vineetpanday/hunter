package com.dtdl.hunter.service;


import com.dtdl.hunter.entity.Vacancy;
import com.dtdl.hunter.model.VacancyDto;
import com.dtdl.hunter.model.VacancyModel;

import java.util.List;

public interface VacancyService {


     String createVacancy(VacancyModel vacancyModel);

     List<Vacancy> getAllVacancies();

     void deleteVacancy(Long id);

    List<VacancyDto> getVacancies();


}

