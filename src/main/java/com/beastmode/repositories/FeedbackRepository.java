package com.beastmode.repositories;

import com.beastmode.models.Feedback;
import com.beastmode.models.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    List<Feedback> findByUserUserId(String userId);
    List<Feedback> findByTrainerTrainerId(String trainerId);
    List<Feedback> findByFeedbackType(FeedbackType type);
    List<Feedback> findByRatingBetween(int min, int max);

}
