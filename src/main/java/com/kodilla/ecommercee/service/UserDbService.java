package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(final User user) {
        user.setActivationKey(ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L));
        LocalDateTime activationKeyExpiration = LocalDateTime.now().plus(Duration.ofHours(1));
        user.setActivationKeyExpiration(activationKeyExpiration);
        return userRepository.save(user);
    }

    private long generateActivationKey() {
        SecureRandom random = new SecureRandom();
        long activationKey = random.nextLong();
        if (activationKey < 0) {
            activationKey = -activationKey;
        }
        return activationKey;
    }



    public Optional<User> getUser(final long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    public Optional<User> getUserByActivationKey(long activationKey) {
        return userRepository.findByActivationKeyAndActivationKeyExpirationAfter(activationKey, LocalDateTime.now());
    }


}