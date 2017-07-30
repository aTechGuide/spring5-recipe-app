package in.kamranali.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne // We have allowed Recipe to own this so haven't specified cascade. As we want to delete recipe notes on deletion of recipe but not vice versa.
	private Recipe recipe;
	
	@Lob // Gets a CLOB field inside DB to persist large objects without having limitation on size of string
    private String recipeNotes;
}
