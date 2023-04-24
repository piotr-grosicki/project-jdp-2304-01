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

    public User generateActivationKey(final UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(UserNotFoundException::new);
        if (!user.getLogin().equals(userDto.getLogin())) {
            throw new UserNotFoundException();
        }
        LocalDateTime activationTime = LocalDateTime.now().plusHours(1);
        UUID activationKey = UUID.randomUUID();
        user.setActivationKey(activationKey);
        user.setActivationKeyExpiration(activationTime);
        userRepository.save(user);

        return user;
    }

    public User blockUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user != null) {
            user.setBlocked(true);
            user.setActivationKey(null);
            user.setActivationKeyExpiration(null);
            userRepository.save(user);
        }
        return user;
    }
}
