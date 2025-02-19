package com.beastmode.services.feedback_service;

import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.FeedbackMapper;
import com.beastmode.models.Feedback;
import com.beastmode.models.FeedbackType;
import com.beastmode.models.Trainer;
import com.beastmode.models.User;
import com.beastmode.repositories.FeedbackRepository;
import com.beastmode.services.trainer_service.TrainerService;
import com.beastmode.services.user_service.UserService;
import com.beastmode.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final UUIDUtil uuidUtil;
    private final UserService userService;
    private final TrainerService trainerService;

    FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, UUIDUtil uuidUtil, UserService userService, TrainerService trainerService){
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.uuidUtil = uuidUtil;
        this.userService = userService;
        this.trainerService = trainerService;
    }

    @Override
    public String createFeedback(String content, Integer rating, FeedbackType feedbackType, String userId, String trainerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        // Validate rating
        if(rating == null || rating < 1 || rating > 5) {
            throw new ApiRequestException("Rating must be between 1 and 5");
        }


        User user = userService.getUserById(userId);
        Trainer trainer = trainerService.getTrainerById(trainerId);

        Feedback feedback = feedbackMapper.toFeedback(
                uuidUtil.generateUuid(), content, rating, feedbackType, user, trainer, LocalDateTime.now(),null);

        feedbackRepository.save(feedback);
        return "Feedback submitted successfully";
    }

    @Override
    public String updateFeedback(String feedbackId, String content, Integer rating, FeedbackType feedbackType, String userId, String trainerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ApiRequestException("Feedback ID does not exist" + feedbackId));


        // Update fields if provided
        if(content != null && !content.isEmpty()) {
            feedback.setContent(content);
        }

        // Validate rating if provided
        if(rating != null) {
            if (rating < 1 || rating > 5) {
                throw new ApiRequestException("Rating must be between 1 and 5");
            }
            feedback.setRating(rating);
        }
        feedback.setFeedbackType(feedbackType);
        feedback.setUpdatedAt(LocalDateTime.now());

        feedbackRepository.save(feedback);
        return "Feedback updated successfully";
    }


    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(String feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ApiRequestException("Feedback not found with ID: " + feedbackId ));
    }

    @Override
    public List<Feedback> getFeedbackByUser(String userId) {
        List<Feedback> feedback = feedbackRepository.findByUserUserId(userId);
        if (feedback.isEmpty()) {
            throw new ApiRequestException("No feedback found for this User ID" + userId);
        }
        return feedback;
    }

    @Override
    public List<Feedback> getFeedbackByTrainer(String trainerId) {
        List<Feedback> feedbacks = feedbackRepository.findByTrainerTrainerId(trainerId);
        if(feedbacks.isEmpty()) {
            throw new ApiRequestException("No feedback found for trainer ID: " + trainerId);
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> getFeedbackByType(FeedbackType feedbackType) {
        List<Feedback> feedbacks = feedbackRepository.findByFeedbackType(feedbackType);
        if(feedbacks.isEmpty()) {
            throw new ApiRequestException("No feedback found for type: " + feedbackType );
        }
        return feedbacks;
    }

    @Override
    public String deleteFeedback(String feedbackId) {
        if(!feedbackRepository.existsById(feedbackId)) {
            throw new ApiRequestException("Feedback not found with ID: " + feedbackId);
        }
        feedbackRepository.deleteById(feedbackId);
        return "Feedback deleted successfully";
    }

    @Override
    public List<Feedback> getFeedbackByRatingRange(int min, int max) {
        return feedbackRepository.findByRatingBetween(min, max);
    }
}
