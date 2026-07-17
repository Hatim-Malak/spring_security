package spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import spring_security.model.User;
import spring_security.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private JwtService jwt;

    @Autowired
    private AuthenticationManager authManager;
    
    public User reqister(User user){
        return repo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwt.generateToken(user.getUsername());
        }
        return "fail";
    }
}
