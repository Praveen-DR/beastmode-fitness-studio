package com.beastmode.controllers;

import com.beastmode.dto.request.CreateTrainerDto;
import com.beastmode.dto.request.UpdateTrainerDto;
import com.beastmode.mappers.TrainerMapper;
import com.beastmode.models.Trainer;
import com.beastmode.services.trainer_service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/api/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @PostMapping("/v1/createTrainer")
    ResponseEntity<String> createTrainer(@RequestBody CreateTrainerDto data){
        String message = trainerService.createTrainer(data.firstName(), data.lastName(), data.email(), data.phoneNumber(), data.specialization(), data.experience(), data.isActive());
        return ResponseEntity.status(201).body(message);
    }

    @PostMapping("/v1/updateTrainer")
    ResponseEntity<String> updateTrainer(@RequestBody UpdateTrainerDto data){
        String message = trainerService.updateTrainer(data.trainerId(), data.firstName(), data.lastName(), data.email(), data.phoneNumber(), data.specialization(), data.experience(), data.isActive());
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/v1/getAllTrainer")
    ResponseEntity<List<Trainer>> getAllTrainer(){
        return ResponseEntity.status(200).body(trainerService.getAllTrainer());
    }

    @GetMapping("/v1/getTrainerById")
    ResponseEntity<Trainer> getTrainerById(@RequestParam("TrainerId") String trainerId){
        return ResponseEntity.status(200).body(trainerService.getTrainerById(trainerId));
    }

    @GetMapping("/v1/deleteTrainer")
    ResponseEntity<String> deleteTrainer(@RequestParam("TrainerId") String trainerId){
        return ResponseEntity.status(200).body(trainerService.deleteTrainer(trainerId));
    }

}
