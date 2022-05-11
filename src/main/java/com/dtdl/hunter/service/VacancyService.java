package com.dtdl.hunter.service;


import com.dtdl.hunter.entity.Vacancy;
import com.dtdl.hunter.model.VacancyModel;

import java.util.List;

public interface VacancyService {


    public String createVacancy(VacancyModel vacancyModel);

    public List<Vacancy> getAllVacancies();

    public void deleteVacancy(Long id);


}

