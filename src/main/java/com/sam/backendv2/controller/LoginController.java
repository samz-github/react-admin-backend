package com.sam.backendv2.controller;

import com.sam.backendv2.dao.RoleDao;
import com.sam.backendv2.entity.AuthenticationResponse;
import com.sam.backendv2.entity.Role;
import com.sam.backendv2.entity.UserCredentials;
import com.sam.backendv2.exception.FailedToLoginException;
import com.sam.backendv2.security.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserAuthenticationService authenticationService;

    @Autowired
    RoleDao roleDao;

    //@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse userLogin(@RequestBody UserCredentials userCredentials) throws FailedToLoginException {

        if (userCredentials == null || (userCredentials.getUsername() == null || userCredentials.getPassword() == null)) {
            throw new FailedToLoginException("Missing login credentials ");
        }

        String username = userCredentials.getUsername();
        String token = authenticationService.authenticateUser(username, userCredentials.getPassword());

        if (token != null) {
            Role role = roleDao.selectAllByUsername(username);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(username,role.getRoleName(),token,"SUCCESS");
            return authenticationResponse;
        }
        throw new FailedToLoginException(String.format(" unable to authenticate user [%s] ", userCredentials.getUsername()));
    }
}
