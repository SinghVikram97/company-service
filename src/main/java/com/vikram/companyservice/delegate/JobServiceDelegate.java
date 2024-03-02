package com.vikram.companyservice.delegate;

import com.vikram.companyservice.client.JobServiceClient;
import com.vikram.companyservice.exception.DownstreamServiceException;
import com.vikram.companyservice.model.Job;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceDelegate {
    private final JobServiceClient jobServiceClient;

    public List<Job> getAllJobsByCompany(Long companyId) {
        ResponseEntity<List<Job>> response ;
        try{
            response = jobServiceClient.getAllJobsByCompany(companyId);
            return response.getBody();
        } catch (FeignException feignException){
            throw new DownstreamServiceException("Job",feignException.status(),feignException.getMessage());
        }
    }
}
