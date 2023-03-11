package com.codewithchristian.libraryapi.service;


import com.codewithchristian.libraryapi.models.users.enums.ERole;
import com.codewithchristian.libraryapi.payload.request.LoginRequest;
import com.codewithchristian.libraryapi.payload.request.RegistrationRequest;
import com.codewithchristian.libraryapi.payload.response.LoginResponse;
import com.codewithchristian.libraryapi.payload.response.RegistrationResponse;

public interface UserService {

    RegistrationResponse registration(RegistrationRequest userRegistrationRequest, ERole eRole);

    LoginResponse login(LoginRequest loginRequest);
}
