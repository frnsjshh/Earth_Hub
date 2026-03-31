package com.francis.earthhub.user.dto;

import com.francis.earthhub.user.AppUser;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(AppUser user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getJoinDate()
        );
    }

    public static AppUser toEntity(UserRequestDTO request) {
        AppUser user = new AppUser();
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setRole(request.role());
        user.setPassword(request.password());
        return user;
    }
}
