package com.francis.earthhub.user;

import com.francis.earthhub.user.dto.UserMapper;
import com.francis.earthhub.user.dto.UserRequestDTO;
import com.francis.earthhub.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return UserMapper.toResponseDTO(userService.saveUser(UserMapper.toEntity(userRequestDTO)));
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return UserMapper.toResponseDTO(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO){
        return UserMapper.toResponseDTO(userService.updateUser(id, UserMapper.toEntity(userRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
