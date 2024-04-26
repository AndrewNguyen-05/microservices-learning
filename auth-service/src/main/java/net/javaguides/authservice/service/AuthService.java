package net.javaguides.authservice.service;

import net.javaguides.authservice.dto.LoginDto;
import net.javaguides.authservice.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
