package com.task.dronetask.entity;

import lombok.*;

import javax.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
