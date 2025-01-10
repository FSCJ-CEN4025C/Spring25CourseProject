package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryInMemoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUser_ShouldPersistUserInDatabase() {

        User user = new User();
        user.setUsername("Tom1");
        user.setEmail("tom1gmail.com");
        user.setAge(21);
        user.setTotalExpenses("5000");
        user.setTotalIncome("10000");
        user.setSalt("salt");
        user.setHash("hash");


        User savedUser = userRepository.save(user);

        User foundUser = entityManager.find(User.class, savedUser.getId());
        assertThat(foundUser).isEqualTo(savedUser);

    }

    @Test
    public void findByUsername_ShouldReturnUser_WhenUsernameExists() {
        User user = new User();
        user.setUsername("Tom2");
        user.setEmail("tom2@gmail.com");
        user.setAge(21);
        user.setTotalExpenses("5000");
        user.setTotalIncome("10000");
        user.setSalt("salt");
        user.setHash("hash");

        entityManager.persistAndFlush(user);

        User foundUser = userRepository.findByUsername("Tom2");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenUserDoesNotExist() {
        Integer nonExistentId = 1000;
        Optional<User> foundUser = userRepository.findById(nonExistentId);
        assertThat(foundUser).isEmpty();
    }


}
