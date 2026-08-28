package com.uap.proiv.jobs.dto;

import java.util.List;

public class UserJobAssigned {
    private List<User> users;
    private Job job;

    public UserJobAssigned(List<User> users2, Job job) {
        this.users = users2;
        this.job = job;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
