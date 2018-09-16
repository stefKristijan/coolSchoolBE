package hr.ferit.coolschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    @NotNull(message = "Unesite korisničko ime")
    @NotBlank(message = "Unesite korisničko ime")
    @Pattern(regexp = "^[A-Za-z0-9]+(?:[_-][A-Za-z0-9]+)*$",
            message = "Unesite ispravno korisničko ime")
    private String username;
    //    @NotBlank(message = "Unesite zaporku")
    //    @NotNull(message = "Unesite zaporku")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Email(message = "Unesite e-mail adresu pravilnog formata")
    @NotNull(message = "Unesite e-mail adresu")
    private String email;
    @NotBlank(message = "Unesite vaše ime")
    @NotNull(message = "Unesite vaše ime")
    private String firstName;
    @NotBlank(message = "Unesite vaše ime")
    @NotNull(message = "Unesite vaše ime")
    private String lastName;
    private Role role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<UserSchool> userSchools;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<QuizParticipant> participants;

    public User(String username, String password, String email, String firstName, String lastName, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", userSchools=" + userSchools +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;
        if (!username.equals(user.username)) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + role.hashCode();
        return result;
    }

    public Set<QuizParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<QuizParticipant> participants) {
        this.participants = participants;
    }

    public Set<UserSchool> getUserSchools() {
        return userSchools;
    }

    public void setUserSchools(Set<UserSchool> userSchools) {
        this.userSchools = userSchools;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
