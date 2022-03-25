package com.example.postservice.feign;

import com.example.postservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserService {
    @GetMapping("/{userId}")
    public User findByID(@PathVariable("userId") String userId);
}
