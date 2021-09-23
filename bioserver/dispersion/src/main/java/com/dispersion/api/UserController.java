package com.dispersion.api;

import com.dispersion.model.LoginData;
import com.dispersion.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @PostMapping(value = "/add", produces = {"application/json"})
    public ResponseEntity<String> addUser(@RequestBody LoginData loginData) throws SQLException {
        final Boolean userAdded = userService.addUser(loginData);

        if (userAdded) {
            return ResponseEntity.ok().body("User added successfully");
        } else {
            return ResponseEntity.badRequest().body("Given username already exists");
        }
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
