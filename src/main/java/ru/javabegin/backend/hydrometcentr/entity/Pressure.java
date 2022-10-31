package ru.javabegin.backend.hydrometcentr.entity;

import javax.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pressure", schema = "weather", catalog = "hydrometcentr")
public class Pressure {

    private String title;
    private Long value;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town townId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pressure pressure = (Pressure) o;
        return Objects.equals(title, pressure.title) && Objects.equals(id, pressure.id) && Objects.equals(value, pressure.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, value);
    }
}
