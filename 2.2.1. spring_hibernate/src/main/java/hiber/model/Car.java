package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;
    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }
    public Car() {

    }
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }
}
