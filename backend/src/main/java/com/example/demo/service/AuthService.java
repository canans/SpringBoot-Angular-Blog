package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.AuthenticationResponse;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
	@Autowired
	private ModelMapper modelMapper;
    

    public Boolean signup(RegisterRequest registerRequest) throws Exception{
    	try {
            User user = modelMapper.map(registerRequest, User.class);

            user.setRealPassword(registerRequest.getPassword());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            userRepository.save(user);
            return true;
		} catch (Exception e) {
			throw new NotFoundException("errorr");
		}
    }

    @Transactional
	public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {

        Authentication authenticate = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	}


	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
	}


}
