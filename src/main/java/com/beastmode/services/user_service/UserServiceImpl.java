package com.beastmode.services.user_service;

import com.beastmode.models.Membership;
import com.beastmode.models.Role;
import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.UserMapper;
import com.beastmode.models.User;
import com.beastmode.repositories.UserRepository;
import com.beastmode.services.membership_service.MembershipService;
import com.beastmode.utils.UUIDUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MembershipService membershipService;
    private final UUIDUtil uuidUtil;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MembershipService membershipService, UUIDUtil uuidUtil){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.membershipService = membershipService;
        this.uuidUtil = uuidUtil;
    }


    @Override
    public String createUser(String email, String name, String password, Role role, String phoneNo, String membershipId) {
        try {

            if (userRepository.existsByEmail(email)) {
                throw new ApiRequestException("emailId already exists");
            }
            Membership membership = membershipService.getMembershipById(membershipId);
            User user = userMapper.toUser(uuidUtil.generateUuid(), email, name, password, role, phoneNo, membership);
            userRepository.save(user);
            return "User Created successfully";
        }catch (HttpMessageNotReadableException e) {
            throw new ApiRequestException("Accepted values: [MEMBER, TRAINER, ADMIN] No other values are permitted.");
        }
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("User not found"));
        if(!user.getPassword().equals(password)){
            throw new ApiRequestException("Credentials do not match");
        }

        return user;
    }

    @Override
    public String updateUser(String userId, String email, String name, String password, Role role) {
        User user = userRepository.findById(userId).orElseThrow(()->new ApiRequestException("User not exist"));
        user.setUserId(userId);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        return "User updated Successfully";
    }

    @Override
    public String deleteUser(String userId) {
        if(!userRepository.existsById(userId)){
            throw new ApiRequestException("User not found");
        }
        userRepository.deleteById(userId);
        return "User deleted Successfully";
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ApiRequestException("User ID not found"));

    }

    @Override
    public List<User> getAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

}
