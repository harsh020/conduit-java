package com.example.conduit.user;

import com.example.conduit.user.exceptions.InvalidPasswordException;
import com.example.conduit.user.exceptions.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;


@Service
public class UserService {
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createNewUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User verifyUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("");
        }
//        else if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            throw new InvalidPasswordException("");
//        }
        return user;
    }

    public User getUserById(Long id) {
        try {
            User user = userRepository.findById(id).get();
            if(user.getIs_active()) {
                return user;
            }
            throw new UserNotFoundException("");
        }
        catch (UserNotFoundException | EntityNotFoundException | NoSuchElementException e) {
            throw new UserNotFoundException("User with id: " + id + "does not exist");
        }
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) throw new UserNotFoundException("User does not exist");
        return user;
    }

    public User updateUser(User user, String username, String password, String email, String bio, String image) {
        if(user.getUsername() != null) user.setUsername(username);
        if(user.getEmail() != null) user.setPassword(password);
        if(user.getEmail() != null) user.setEmail(email);
        if(user.getBio() != null) user.setBio(bio);
        if(user.getImage() != null) user.setImage(image);
        userRepository.save(user);
        return user;
    }

    public User followUser(User user, String follow) {
        User follow_user = userRepository.findUserByUsername(follow);
        user.followUser(follow_user);
        follow_user.followedBy(user);
        userRepository.save(user);
        userRepository.save(follow_user);
        return user;
    }

    public User delete(Long id) {
        User user = getUserById(id);
        user.setIs_active(Boolean.FALSE);
        return user;
    }
}
