package com.uap.proiv.jobs.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;

import com.uap.proiv.jobs.client.JobApiRepository;
import com.uap.proiv.jobs.dto.AssignedResponse;
import com.uap.proiv.jobs.dto.Job;
import com.uap.proiv.jobs.dto.User;
import com.uap.proiv.jobs.dto.UserApiResponse;
import com.uap.proiv.jobs.dto.UserJobAssigned;
import com.uap.proiv.jobs.service.AssignedService;
import com.uap.proiv.jobs.service.JobService;
import com.uap.proiv.jobs.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)

public class UserJobAssignedServiceImplTest {
    @Mock
    JobService jobService;

    @Mock
    UserService userService;

    @Mock
    AssignedService assignedService;

    @InjectMocks
    UserJobAssignedServiceImpl serviceImpl;

    List<Job> jobs;
    List<User> users;
    List<AssignedResponse> assignedResponses;
    UserApiResponse userApiResponse;

    @BeforeEach
    void setup() {
        jobs = new ArrayList<>();
        Job job1 = new Job();
        job1.setId(1);  
        job1.setName("Developer");
        job1.setSalary(500);
        job1.setHours(80);
        jobs.add(job1);

        Job job2 = new Job();
        job2.setId(2);
        job2.setName("Tester");
        job2.setSalary(400);
        job2.setHours(75);
        jobs.add(job2);

        users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user@gmail.com");
        user1.setAvatar("null");
        user1.setFirstName("Eve");
        user1.setLastName("Inostroza");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user@gmail.com");
        user2.setAvatar("null");
        user2.setFirstName("Enzo");
        user2.setLastName("Inostroza");
        users.add(user2);
        
        userApiResponse = new UserApiResponse();
        userApiResponse.setData(users);

        assignedResponses = new ArrayList<>();
        assignedResponses.add(new AssignedResponse(1, 2));
        assignedResponses.add(new AssignedResponse(2, 1));
    }

    @Test
    @DisplayName("Verifica la respuesta de una sola pagina de usuarios y asignaciones de trabajo")
    void assign_succesOnePage(){
        when(jobService.getAllJobs()).thenReturn(jobs);
        when(userService.search(1)).thenReturn(userApiResponse);
        when(assignedService.create(jobs, List.of(1,2))).thenReturn(assignedResponses);

        List<UserJobAssigned> result = serviceImpl.assign();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUsers().get(0).getId());

        verify(jobService, times(1)).getAllJobs();
        verify(userService, times(1)).search(1);
        verify(assignedService, times(1)).create(jobs, List.of(10,20));
    }

}