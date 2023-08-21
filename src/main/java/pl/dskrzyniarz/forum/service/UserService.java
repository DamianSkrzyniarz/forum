package pl.dskrzyniarz.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dskrzyniarz.forum.dto.UserDto;
import pl.dskrzyniarz.forum.entity.User;
import pl.dskrzyniarz.forum.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User saveUser(UserDto userDto){
        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setEnabled(false);
        newUser.setLocked(false);
        newUser.setRoles("ROLE_USER");

        return userRepository.save(newUser);
    }

    public User GetUser(int id){
        return userRepository.findById(id).get();
    }

    public List<User> GetAllUsers(){
        return userRepository.findAll();
    }

    public User editUser(User newUser, int userId){
        User oldUser = userRepository.findById(userId).get();
        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(oldUser);
    }

    public void deleteUser (int userId){
        userRepository.deleteById(userId);
    }

    public void activateUser(int userId){
        User user = userRepository.findById(userId).get();
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void activateUser(User user){
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void lockUser(int userId){
        User user = userRepository.findById(userId).get();
        user.setLocked(true);
        userRepository.save(user);
    }

}
