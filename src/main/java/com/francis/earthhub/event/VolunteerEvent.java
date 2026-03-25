package com.francis.earthhub.event;

import com.francis.earthhub.user.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name = "volunteer_events")
public class VolunteerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volunteer_event_seq")
    @SequenceGenerator( name = "volunteer_event_seq",
                        sequenceName = "volunteer_event_seq",
                        initialValue = 5000)
                        //allocationSize = 1) // default is 50
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn (name = "organizer_id", nullable = false)
    private AppUser organizer;
}

