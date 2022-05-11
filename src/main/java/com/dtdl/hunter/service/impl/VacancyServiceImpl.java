package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.Vacancy;
import com.dtdl.hunter.model.VacancyModel;
import com.dtdl.hunter.repository.VacancyRepository;
import com.dtdl.hunter.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public String createVacancy(VacancyModel vacancyModel) {
        Vacancy vacancyEntity = new Vacancy();

        vacancyEntity.setCreated_by(vacancyModel.getCreated_by());
        vacancyEntity.setDescription(vacancyModel.getDescription());
        vacancyEntity.setExperience(vacancyModel.getExperience());
        vacancyEntity.setLocation(vacancyModel.getLocation());
        vacancyEntity.setOpenPositions(vacancyModel.getOpenPositions());
        vacancyEntity.setRole(vacancyModel.getRole());

        vacancyRepository.save(vacancyEntity);

        String jobId = "DTDL-".concat(String.valueOf(vacancyEntity.getId())).concat("-").concat(vacancyModel.getRole());
        return jobId;

    }


    public List<Vacancy> getAllVacancies(){
        Iterable<Vacancy> allVacancies= vacancyRepository.findAll();
        List<Vacancy> allVacanciesList = new ArrayList<>();

        allVacancies.forEach( a->{
            String jobId = "DTDL-".concat(String.valueOf(a.getId())).concat("-").concat(a.getRole());
            a.setJobId(jobId);
            allVacanciesList.add(a);
        });

        return  allVacanciesList;
    }

    @Override
    public void deleteVacancy(Long id) {
        vacancyRepository.deleteById(id);
    }
}
