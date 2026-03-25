package com.francis.earthhub.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.francis.earthhub.event.VolunteerEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_users")
public class AppUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100)
    private String firstName;
    @Column (nullable = false, length = 100)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate joinDate;

    @OneToMany(mappedBy = "organizer")
    @JsonIgnore
    private List<VolunteerEvent> volunteerEvents;
}
