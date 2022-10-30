package ru.javabegin.backend.hydrometcentr.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fallout",schema = "weather", catalog = "hydrometcentr")
public class Fallout {
    private String form;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fallout fallout = (Fallout) o;
        return Objects.equals(form, fallout.form) && Objects.equals(id, fallout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(form, id);
    }
}
