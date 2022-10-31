package ru.javabegin.backend.hydrometcentr.entity;

import javax.persistence.*;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "temperature_id", referencedColumnName = "id")
    private Temperature temperatureId;
    @ManyToOne
    @JoinColumn(name = "pressure_id", referencedColumnName = "id")
    private Pressure pressureId;
@ManyToOne
    @JoinColumn(name = "moisture_id",referencedColumnName = "id")
    private Moisture moistureId;
    @ManyToOne
    @JoinColumn(name = "fallout_id", referencedColumnName = "id")
    private Fallout falloutId;


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
