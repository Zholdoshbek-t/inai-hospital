package com.inai.hospital.entity;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inn;

    private String password;

    private Boolean active;

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer age;

    private Boolean isFemale;

    @ManyToOne
    @JoinColumn(
            name = "role_id"
    )
    private Role role;

    @OneToMany(mappedBy = "patient")
    private List<DiseaseRegistered> patientsDiseases = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    private List<DiseaseRegistered> doctorsDiseases = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions().stream()
                .map(
                        permission -> new SimpleGrantedAuthority(permission.getName())
                )
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return inn;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public static UserDetails getUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getInn(),
                user.getPassword(),
                user.getActive(),
                user.getActive(),
                user.getActive(),
                user.getActive(),
                user.getAuthorities()
        );
    }
}
