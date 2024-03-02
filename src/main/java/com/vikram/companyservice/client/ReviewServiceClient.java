package com.vikram.companyservice.client;

import com.vikram.companyservice.model.Job;
import com.vikram.companyservice.model.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${review.service.name}", url = "${review.service.url}")
public interface ReviewServiceClient {
    @GetMapping("${review.service.basepath}/company")
    ResponseEntity<List<Review>> getAllReviewsForCompany(@RequestParam("company_id") Long companyId);
}
