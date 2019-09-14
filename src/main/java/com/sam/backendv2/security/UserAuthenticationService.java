package com.sam.backendv2.security;
import com.sam.backendv2.dao.RoleDao;
import com.sam.backendv2.dao.UserDao;
import com.sam.backendv2.entity.Role;
import com.sam.backendv2.entity.User;
import com.sam.backendv2.exception.FailedToLoginException;
import com.sam.backendv2.exception.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@Component
public class UserAuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    public String authenticateUser(String username, String password) throws FailedToLoginException {
        boolean isAuthenticated = false;

        Optional<User> optionalUser = userDao.findByUsername(username);
        if(optionalUser.isPresent()){
            String encryptPassword = optionalUser.get().getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean isMatch = bCryptPasswordEncoder.matches(password, encryptPassword);
            if(isMatch) {
                isAuthenticated = true;
            }
        }

        if (isAuthenticated) {
            try {
                return jwtService.generateToken(username);
            } catch (URISyntaxException | IOException e) {
                throw new FailedToLoginException(e.getMessage());
            }
        }
        throw new FailedToLoginException(String.format("unable to authenticate user [%s]", username));
    }

    public Role authenticateToken(String jwtToken) throws JwtAuthenticationException {

        try {
            String username = jwtService.verifyToken(jwtToken);
           // List<String> userRoles = userService.getUserRoles(username);
            Role role = roleDao.selectAllByUsername(username);
            return role;
        } catch (IOException | URISyntaxException e) {
            throw new JwtAuthenticationException(e.getMessage(), e);
        }
    }
}
