package br.com.bichofull.bichofull.service.deposit;

import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DepositService depositService;

    @Test
    @DisplayName("Should add value to balance")
    void deposit(){
        User user = new User("test", "test@gmail.com", "test123");
        user.setBalance(new BigDecimal("100.00"));
        BigDecimal value = new BigDecimal("40.00");
        BigDecimal expected = user.getBalance().add(value);

        depositService.deposit(user, value);
        assertEquals(expected, user.getBalance());
        verify(userRepository).save(user);
    }

}