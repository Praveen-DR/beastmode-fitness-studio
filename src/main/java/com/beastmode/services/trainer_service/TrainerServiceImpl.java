package com.beastmode.services.trainer_service;

import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.TrainerMapper;
import com.beastmode.models.Trainer;
import com.beastmode.repositories.TrainerRepository;
import com.beastmode.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final UUIDUtil uuidUtil;

    TrainerServiceImpl(TrainerRepository trainerRepository, TrainerMapper trainerMapper, UUIDUtil uuidUtil){
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
        this.uuidUtil = uuidUtil;
    }


    @Override
    public String createTrainer(String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive, String password) {
        if (trainerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ApiRequestException("Phone number Already exits");
        }
        Trainer trainer = trainerMapper.toTrainer(uuidUtil.generateUuid(), firstName, lastName, email, phoneNumber, specialization, experience, isActive, password);
        trainerRepository.save(trainer);
        return "Trainer created Successfully";
    }

    @Override
    public String updateTrainer(String trainerId, String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive, String password) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(()->new ApiRequestException("Trainer ID Not found"));
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setEmail(email);
        trainer.setPhoneNumber(phoneNumber);
        trainer.setSpecialization(specialization);
        trainer.setExperience(experience);
        trainer.setPassword(password);
        trainerRepository.save(trainer);
        return "Trainer Updated Successfully";
    }

    @Override
    public String deleteTrainer(String trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(()->new ApiRequestException("Trainer ID is not found"));
        trainerRepository.deleteById(trainerId);
        return "Trainer Deleted Successfully";
    }

    @Override
    public List<Trainer> getAllTrainer() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainerById(String trainerId) {
        return trainerRepository.findById(trainerId).orElseThrow(()->new ApiRequestException("Trainer ID Not found"));
    }
}
