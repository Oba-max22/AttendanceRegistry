package com.obamax.AttendanceRegistry.model;

import com.obamax.AttendanceRegistry.model.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.ABSENT;

    private boolean signedIn = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signedInTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signedOutTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @PrePersist
    private void setDateCreated() {
        dateCreated = new Date();
    }

}
