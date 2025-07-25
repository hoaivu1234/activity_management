package com.ltm.extracurricular.entity;
import com.ltm.extracurricular.common.RegistrationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "activity_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status; // PENDING, APPROVED, REJECTED

    @Column(name = "registration_time")
    private LocalDateTime registrationTime;
}

