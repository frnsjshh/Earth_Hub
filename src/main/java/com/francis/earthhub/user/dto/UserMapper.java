package com.francis.earthhub.user.dto;

import com.francis.earthhub.user.AppUser;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(AppUser user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getJoinDate()
        );
    }

    public static AppUser toEntity(UserRequestDTO request) {
        AppUser user = new AppUser();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setRole(request.role());
        return user;
    }
}
