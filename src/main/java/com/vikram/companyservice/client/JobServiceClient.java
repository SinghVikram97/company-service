package com.vikram.companyservice.client;

import com.vikram.companyservice.model.Job;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "${job.service.name}")
public interface JobServiceClient {
    @GetMapping("${job.service.basepath}/company")
    @CircuitBreaker(name = "jobBreaker", fallbackMethod = "getAllJobsByCompanyFallback")
    @Retry(name = "jobServiceRetry")
    ResponseEntity<List<Job>> getAllJobsByCompany(@RequestParam("company_id") Long companyId);

    default ResponseEntity<List<Job>> getAllJobsByCompanyFallback(Long companyId, Exception exception) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
