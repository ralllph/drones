package com.task.dronetask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "battery_history")
public class BatteryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "battery_percentage")
    private int batteryPercent;

    @Column(name = "time")
    private String time;


    @ManyToOne
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drones drone;
}
