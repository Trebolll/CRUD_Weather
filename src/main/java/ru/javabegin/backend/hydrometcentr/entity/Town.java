package ru.javabegin.backend.hydrometcentr.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "town", schema = "weather", catalog = "hydrometcentr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Town {

    private String name;

    private Long population;

    private String country;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "temperature_id")
    private Long temperatureId;

    @Column(name = "pressure_id")
    private Long pressureId;

    @Column(name = "moisture_id")
    private Long moistureId;

    @Column(name = "fallout_id")
    private Long falloutId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(name, town.name) && Objects.equals(population, town.population) && Objects.equals(country, town.country) && Objects.equals(id, town.id) && Objects.equals(temperatureId, town.temperatureId) && Objects.equals(pressureId, town.pressureId) && Objects.equals(moistureId, town.moistureId) && Objects.equals(falloutId, town.falloutId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, population, country, id, temperatureId, pressureId, moistureId, falloutId);
    }
}
