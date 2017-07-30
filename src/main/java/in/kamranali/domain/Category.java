package in.kamranali.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Get the ID from DB upon persistence 
	private Long id;
	
	private String description;
	
	@ManyToMany(mappedBy = "categories") // "categories" is the property name in Recipe class
	private Set<Recipe> recipes;
}
