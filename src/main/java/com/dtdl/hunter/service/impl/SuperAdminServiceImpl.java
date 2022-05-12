package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.model.TalentAquisitionDto;
import com.dtdl.hunter.repository.CandidateRepository;
import com.dtdl.hunter.repository.EmployeeRepository;
import com.dtdl.hunter.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    public List<TalentAquisitionDto> getTalentAquisitionData(){

        List<com.dtdl.hunter.entity.Employee> hrEmployees = employeeRepository.findAllByDesignationAndSpeciality("HR", "Recruitment");

        List<TalentAquisitionDto> dtoList = new ArrayList<>();
        hrEmployees.forEach( a -> {
                TalentAquisitionDto dto = new TalentAquisitionDto();
                long aligned   = candidateRepository.countByHrSpoc(a.getName());
                long inProcess = candidateRepository.countByHrSpocAndStatus(a.getName(), StringConstant.Status.InReview.value);
                List<String> statusList = Arrays.asList( StringConstant.Status.Selected.value,StringConstant.Status.Rejected.value ,StringConstant.Status.CandidateNotInterested.value);
                long closed = candidateRepository.countByHrSpocAndStatusIn(a.getName(), statusList);

                dto.setAligned((int) aligned);
                dto.setRecruiter(a.getName());
                dto.setInProgress((int) inProcess);
                dto.setClosed((int) closed);
                dtoList.add(dto);


        });

        return dtoList;

    }

}
