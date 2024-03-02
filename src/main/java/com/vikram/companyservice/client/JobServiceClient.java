package com.vikram.companyservice.client;

import com.vikram.companyservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${job.service.name}", url = "${job.service.url}")
public interface JobServiceClient {
    @GetMapping("${job.service.basepath}/company")
    ResponseEntity<List<Job>> getAllJobsByCompany(@RequestParam("company_id") Long companyId);
}
