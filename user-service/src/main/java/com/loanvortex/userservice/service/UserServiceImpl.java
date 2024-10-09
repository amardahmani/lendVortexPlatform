package com.loanvortex.userservice.service;

import com.loanvortex.userservice.dto.UserRequestDTO;
import com.loanvortex.userservice.dto.UserResponseDTO;
import com.loanvortex.userservice.mapper.UserMapper;
import com.loanvortex.userservice.model.User;
import com.loanvortex.userservice.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Iterable<UserResponseDTO> getAllUsers() {
        // Convert Iterable<User> to List<UserResponseDTO>
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        // Convert User entity to UserResponseDTO
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        Customer customer;
        try {
            customer = createCustomer(userRequestDTO);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe customer", e);
        }

        // Set Stripe customerId in the user request DTO
        userRequestDTO.setCustomerId(customer.getId());

        // Convert UserRequestDTO to User entity and save it
        User user = userMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);

        // Convert saved User entity to UserResponseDTO
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponseDTO editUser(UserRequestDTO userRequestDTO,Long userId) {
        Optional<User> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update the fields
            existingUser.setUsername(userRequestDTO.getUsername());
            existingUser.setEmail(userRequestDTO.getEmail());
            existingUser.setRole(userRequestDTO.getRole());
            existingUser.setPassword(userRequestDTO.getPassword()); // Handle encryption if needed

            User updatedUser = userRepository.save(existingUser);

            // Convert updated User entity to UserResponseDTO
            return userMapper.toResponse(updatedUser);
        } else {
            throw new RuntimeException("User not found with id " + userId);
        }
    }

    private Customer createCustomer(UserRequestDTO userRequestDTO) throws StripeException {
        // Create a Stripe customer using email from UserRequestDTO
        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                .setEmail(userRequestDTO.getEmail())
                .build();
        return Customer.create(customerCreateParams);
    }
}
