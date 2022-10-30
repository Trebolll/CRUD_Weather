package ru.javabegin.backend.hydrometcentr.entity;

import javax.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "moisture",schema = "weather", catalog = "hydrometcentr")
public class Moisture {

    private String title;
    @Column(name = "value_%")
    private Long value;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moisture moisture = (Moisture) o;
        return Objects.equals(title, moisture.title) && Objects.equals(id, moisture.id) && Objects.equals(value, moisture.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, value);
    }
}
