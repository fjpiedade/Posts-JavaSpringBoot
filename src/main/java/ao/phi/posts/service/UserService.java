package ao.phi.posts.service;

import ao.phi.posts.exception.PostNotFoundException;
import ao.phi.posts.model.PostModel;
import ao.phi.posts.model.TokenModel;
import ao.phi.posts.model.UserModel;
import ao.phi.posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String USER_EMAIL_NOT_FOUND_MSG =
            "User with email %s not found";

    public UserService() {
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_EMAIL_NOT_FOUND_MSG, email)));
        //return user;
        return new User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getAuthorities());
    }

    public boolean checkEmail(String email) {
        boolean check = false;
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_EMAIL_NOT_FOUND_MSG, email)));
        check = true;
        return check;
    }


    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }


    @Transactional
    public UserModel signUpUser(UserModel user) {
        /*boolean isValidEmail = emailValidator.test(user.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }*/

        boolean userEmailExist = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if (userEmailExist) {
            throw new IllegalStateException("Email already Exist!");
        }

        userRepository.save(user);

        //Send  confirmation Token


        //send email

        return user;
    }

    @Transactional
    public UserModel enableUserByTokenConfirmed(UUID id) throws IllegalStateException {
        //TokenModel tokenModel = tokenService.getByToken(token);
        UserModel user = userRepository.findByUserId(id);
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
            return user;
        } else {
            throw new IllegalStateException("User not founded with the id: " + id);
        }
    }
}
