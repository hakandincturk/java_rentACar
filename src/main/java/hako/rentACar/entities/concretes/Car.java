package hako.rentACar.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cars")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "plate", unique = true)
  private String plate;

  @Column(name = "daily_price")
  private double dailyPrice;

  @Column(name = "model_year")
  private int modelYear;

  @Column(name = "State")
  private int state; // 1 -> Available, 2 -> Rented, 3 -> Maintanence

  @ManyToOne
  @JoinColumn(name = "model_id")
  private Model model;

  @Column(name = "km")
  private int km;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;
}
