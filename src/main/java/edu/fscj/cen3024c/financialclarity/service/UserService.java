package edu.fscj.cen3024c.financialclarity.service;

import edu.fscj.cen3024c.financialclarity.dto.UserDTO;
import edu.fscj.cen3024c.financialclarity.entity.User;

import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // PBKDF2 configuration
    private static final int SALT_LENGTH = 16; // 128 bits
    private static final int HASH_ITERATIONS = 10000;
    private static final int HASH_KEY_LENGTH = 256; // 256 bits

    public List<User> findAll() { return userRepository.findAll(); }    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {return userRepository.findByUsername(username);}

    @Transactional
    public void deleteByUsername(String username) {userRepository.deleteByUsername(username);}

    public User save( User user ) { return userRepository.save(user); }
    // Save a new user by converting from UserDTO to User entity with password hashing and salting
    public UserDTO save(UserDTO userDTO) {
        // Convert UserDTO to User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setTotalIncome(userDTO.getTotalIncome());
        user.setTotalExpenses(userDTO.getTotalExpense());

        // Generate salt and hash the password
        String salt = generateSalt();
        String hashedPassword = hashPassword(userDTO.getPassword(), salt);

        // Set salt and hashed password in User entity
        user.setSalt(salt);
        user.setHash(hashedPassword);

        // Save the User entity
        User savedUser = userRepository.save(user);

        // Convert saved User entity back to UserDTO and return
        return convertToDTO(savedUser);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getAge(), user.getTotalIncome(), user.getTotalExpenses() );
    }

    // Password hashing logic
    private String hashPassword(String password, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), HASH_ITERATIONS, HASH_KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash); // Store hash as Base64 encoded string
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error during password hashing", e);
        }
    }

    // Generate salt
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); // Store salt as Base64 encoded string
    }
}
