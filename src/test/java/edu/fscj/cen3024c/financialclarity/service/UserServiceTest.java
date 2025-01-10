package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.UserDTO;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.service.UserService;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    //Tests findAll()
    @Test
    void findAll_ShouldReturnUserList(){
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("Test User");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("Test User2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> results = userService.findAll();

        assertThat(results).isNotNull().hasSize(2);
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void saveUserDTO_ShouldSaveUserandReturnUserDTO(){
        //Given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Test User");
        userDTO.setEmail("email@test.com");
        userDTO.setPassword("testABC");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setUsername(userDTO.getUsername());
        savedUser.setEmail(userDTO.getEmail());
        savedUser.setSalt("generatedSalt");
        savedUser.setHash("hashedPassword");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When
        UserDTO result = userService.save(userDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(userDTO.getUsername());
        assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Tests retrieval by ID
    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // Given
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUsername("Test User");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.findById(userId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("Test User");
        verify(userRepository, times(1)).findById(userId);
    }

    // Tests deleteById()
    @Test
    void deleteById_ShouldDeleteUser() {
        // Given
        Integer userId = 1;

        // When
        userService.deleteById(userId);

        // Then
        verify(userRepository, times(1)).deleteById(userId);
    }
}