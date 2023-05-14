package org.sfec.controller.expert;

import org.sfec.entity.Expert;
import org.sfec.repository.expert.ExpertRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/experts")
public class ExpertController {

    private ExpertRepository expertRepository;

    public ExpertController(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Expert>> findAllExperts(){
        List<Expert> experts = expertRepository.findAll();

        return new ResponseEntity<>(experts, HttpStatus.OK);
    }
}
