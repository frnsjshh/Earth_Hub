package com.francis.earthhub.event;


import com.francis.earthhub.user.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
    @SequenceGenerator( name = "attendance_seq", sequenceName = "attendance_seq")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime registrationDateTime;
    @Column(nullable = false)
    private Boolean participated = false;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private VolunteerEvent event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

}
