package com.loanvortex.userservice.service;

import com.loanvortex.userservice.model.User;
import com.loanvortex.userservice.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public User saveUser(User user) {
        Customer customer = null;
        try {
            customer = createCustomer(user);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        user.setCustomerId(customer.getId());
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {

        Optional<User> existingUserOpt = userRepository.findById(user.getId());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();


            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setPassword(user.getPassword()); // Handle encryption here if needed


            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + user.getId());
        }
    }

    private Customer createCustomer(User user) throws StripeException {
        CustomerCreateParams customerCreateParams = CustomerCreateParams
                .builder()
                .setEmail(user.getEmail())
                .build();
        return Customer.create(customerCreateParams);
    }
}
