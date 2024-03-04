package com.vikram.companyservice.client;

import com.vikram.companyservice.model.Review;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "${review.service.name}")
public interface ReviewServiceClient {
    @GetMapping("${review.service.basepath}/company")
    @CircuitBreaker(name = "reviewBreaker", fallbackMethod = "getAllReviewsForCompanyFallback")
    @Retry(name = "reviewServiceRetry")
    ResponseEntity<List<Review>> getAllReviewsForCompany(@RequestParam("company_id") Long companyId);

    default ResponseEntity<List<Review>> getAllReviewsForCompanyFallback(Long companyId, Exception exception) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
