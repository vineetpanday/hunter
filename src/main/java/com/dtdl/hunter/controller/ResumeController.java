package com.dtdl.hunter.controller;

import com.dtdl.hunter.entity.Resume;
import com.dtdl.hunter.repository.ResumeRepository;
import com.dtdl.hunter.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ResumeController {


    @Autowired
    private ResumeService resumeService;

    @GetMapping(value="v1/getResume")
    public ResponseEntity<Resource> getResume(@RequestParam Long id){

        Optional<Resume> resume = resumeService.getResume(id);

        if(resume.isPresent()) {
            ByteArrayResource resource = new ByteArrayResource(resume.get().getResume());

            return ResponseEntity.ok()
                   // .contentLength(file.length())
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(resource);


        }

return null;

    }
}
