package spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_security.model.User;
import spring_security.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public User reqister(User user){
        return repo.save(user);
    }
}
