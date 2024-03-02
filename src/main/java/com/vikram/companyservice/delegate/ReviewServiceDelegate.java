package com.vikram.companyservice.delegate;

import com.vikram.companyservice.client.JobServiceClient;
import com.vikram.companyservice.client.ReviewServiceClient;
import com.vikram.companyservice.exception.DownstreamServiceException;
import com.vikram.companyservice.model.Job;
import com.vikram.companyservice.model.Review;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceDelegate {
    private final ReviewServiceClient reviewServiceClient;
    public List<Review> getAllReviewsForCompany(Long companyId) {
        ResponseEntity<List<Review>> response ;
        try{
            response = reviewServiceClient.getAllReviewsForCompany(companyId);
            return response.getBody();
        } catch (FeignException feignException){
            throw new DownstreamServiceException("Review",feignException.status(),feignException.getMessage());
        }
    }
}
