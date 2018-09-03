package hr.ferit.coolschool.service;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.exception.UserAlreadyExistsException;
import hr.ferit.coolschool.model.User;
import hr.ferit.coolschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Korisnik ne postoji");
        }

        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.get().getRole().toString()))
        );
    }

    @Override
    public User save(User user) {
        if (!this.userRepository.findByUsername(user.getUsername()).isPresent()) {
            //TODO - when school Repository is added, check if there are schools in object and add them to DB
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return this.userRepository.save(user);
        }
        throw new UserAlreadyExistsException("Korisnik s odabranim korisničkim imenom već postoji");
    }

    @Override
    public User update(Long id, User user) {
        User persisted = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traženi korisnik ne postoji"));

        user.setUserId(id);
        user.setPassword(persisted.getPassword());

        this.userRepository.findByUsername(user.getUsername()).ifPresent(
                u -> {
                    if (!u.getUserId().equals(id) && u.getUsername().equals(user.getUsername()))
                        throw new UserAlreadyExistsException("Korisnik s odabranim korisničkim imenom već postoji");
                }
        );

        return this.userRepository.save(user);
    }

    @Override
    public boolean updatePassword(Long id, String oldPassword, String newPassword) {
        User persisted = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traženi korisnik ne postoji"));

        if (passwordEncoder.matches(oldPassword, persisted.getPassword())) {
            int updateResult = this.userRepository.updatePassword(id, passwordEncoder.encode(newPassword));
            if(updateResult>0){
                return true;
            }
            return false;
        }
        throw new RuntimeException("Unijeli ste pogrešnu trenutnu lozinku");
    }
}

