package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.exception.ResourceNotFoundException;
import hr.ferit.coolschool.model.User;
import hr.ferit.coolschool.repository.UserRepository;
import hr.ferit.coolschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @PostMapping("registration")
    public ResponseEntity<User> registerUser(
            @RequestBody @Valid User user
    ) {
        User newUser = this.userService.save(user);
        if (!Objects.isNull(newUser)) {
            return ResponseEntity.ok(newUser);
        }

        throw new RuntimeException("Nešto je pošlo po zlu pri dodavanju korisnika");
    }

    @GetMapping("{id}")
    public ResponseEntity<User> userById(
            @PathVariable Long id
    ) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        throw new ResourceNotFoundException("Traženi korisnik ne postoji");
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid User user
    ) {
        User updatedUser = this.userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("{id}/update-password")
    public boolean updateUserPassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        return this.userService.updatePassword(id, oldPassword, newPassword);
    }

    //I think this is not needed because the user gets all schools on findById() method
    @GetMapping("{id}/schools")
    public ResponseEntity<?> getUserSchoolList(
            @PathVariable Long id
    ) {
        throw new UnsupportedOperationException("Nije još implementirano");
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(
            @PathVariable("id") Long userId
    ) {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Traženi korisnik ne postoji"));

        this.userRepository.deleteById(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("self")
    public ResponseEntity deleteSelf(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User me = this.userRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("Ovaj korisnički račun ne postoji"));

        this.userRepository.deleteById(me.getUserId());
        SecurityContextHolder.clearContext();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
