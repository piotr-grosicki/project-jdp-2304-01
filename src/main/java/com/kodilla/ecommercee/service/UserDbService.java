package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User generateActivationKey(final User user) throws UserNotFoundException {
        User generatesUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        if (!generatesUser.getLogin().equals(user.getLogin())) {
            throw new UserNotFoundException();
        }

        LocalDateTime activationTime = LocalDateTime.now().plusHours(1);
        UUID activationKey = UUID.randomUUID();
        generatesUser.setActivationKey(activationKey);
        generatesUser.setActivationKeyExpiration(activationTime);

        return userRepository.save(generatesUser);
    }

    public User blockUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setBlocked(true);
        user.setActivationKey(null);
        user.setActivationKeyExpiration(null);
        return userRepository.save(user);
    }
}
