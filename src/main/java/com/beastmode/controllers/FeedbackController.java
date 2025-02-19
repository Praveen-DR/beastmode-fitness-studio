package com.beastmode.controllers;

import com.beastmode.dto.request.CreateFeedbackDto;
import com.beastmode.dto.request.UpdateFeedbackDto;
import com.beastmode.dto.response.FeedBackResponseDto;
import com.beastmode.mappers.FeedbackMapper;
import com.beastmode.models.Feedback;
import com.beastmode.models.FeedbackType;
import com.beastmode.services.feedback_service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    FeedbackController(FeedbackService feedbackService, FeedbackMapper feedbackMapper){
        this.feedbackService = feedbackService;
        this.feedbackMapper = feedbackMapper;
    }

    @PostMapping("/v1/createFeedback")
    public ResponseEntity<String> createFeedback(@RequestBody CreateFeedbackDto data) {
        String message = feedbackService.createFeedback(
                data.content(), data.rating(), data.feedbackType(), data.userId(),
                data.trainerId(), data.createdAt(), data.updatedAt()
        );
        return ResponseEntity.status(201).body(message);
    }

    @PostMapping("/v1/updateFeedback")
    public ResponseEntity<String> updateFeedback(@RequestBody UpdateFeedbackDto data) {
        String message = feedbackService.updateFeedback(
                data.feedbackId(), data.content(), data.rating(), data.feedbackType(),
                data.userId(), data.trainerId(), data.createdAt(), data.updatedAt()
        );
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/v1/getAllFeedback")
    public ResponseEntity<List<FeedBackResponseDto>> getAllFeedback() {
        List<Feedback> feedback = feedbackService.getAllFeedback();
        List<FeedBackResponseDto> response = feedback.stream().map(feedbackMapper::toFeedBackResponseDto).toList();
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/v1/getFeedbackById")
        public ResponseEntity<Feedback> getFeedbackById(@RequestParam("feedbackId") String feedbackId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackById(feedbackId));
    }

    @GetMapping("/v1/getFeedbackByUser")
    public ResponseEntity<List<Feedback>> getFeedbackByUser(@RequestParam("userId") String userId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByUser(userId));
    }

    @GetMapping("/v1/getFeedbackByTrainer")
    public ResponseEntity<List<Feedback>> getFeedbackByTrainer(@RequestParam("trainerId") String trainerId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByTrainer(trainerId));
    }

    @GetMapping("/v1/getFeedbackByType")
    public ResponseEntity<List<Feedback>> getFeedbackByType(@RequestParam("feedbackType") FeedbackType feedbackType) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByType(feedbackType));
    }

    @GetMapping("/v1/deleteFeedback")
    public ResponseEntity<String> deleteFeedback(@RequestParam("feedbackId") String feedbackId) {
        String message = feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/v1/getFeedbackByRatingRange")
    public ResponseEntity<List<Feedback>> getFeedbackByRatingRange(@RequestParam("min") int min, @RequestParam("max") int max) {
        return ResponseEntity.ok(feedbackService.getFeedbackByRatingRange(min, max));
    }
}