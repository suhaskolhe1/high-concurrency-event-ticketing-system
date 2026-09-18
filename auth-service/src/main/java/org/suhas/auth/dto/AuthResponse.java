package org.suhas.auth.dto;
import org.suhas.common.dto.UserDTO;
public record AuthResponse(String token, UserDTO user) {}
