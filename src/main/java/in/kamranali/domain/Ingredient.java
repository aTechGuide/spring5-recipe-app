package in.kamranali.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Get the ID from DB upon persistence 
	private Long id;
	
	private String description;
	private BigDecimal amount;
	
	@ManyToOne // No cascading because if we delete an ingredient we don't want it to delete recipe. 
	private Recipe recipe;
}
