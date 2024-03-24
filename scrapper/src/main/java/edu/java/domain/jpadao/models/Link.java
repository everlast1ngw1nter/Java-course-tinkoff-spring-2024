package edu.java.domain.jpadao.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Column(name = "last_check_time", nullable = false)
    private Timestamp lastCheckTime;
}
