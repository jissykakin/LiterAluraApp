package com.challenger.literAlura.services;

import com.challenger.literAlura.dtos.UserDTO;
import com.challenger.literAlura.models.User;
import com.challenger.literAlura.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserByID(Long id) {
        Optional<User> userResult = userRepository.findById(id);

        if(userResult.isPresent()){
            User user =  userResult.get();
            return  new UserDTO(user.getId(),user.getUsername(), user.getEmail());
        }else{
            return null;
        }
    }
}
