package in.kamranali.repositories;

import org.springframework.data.repository.CrudRepository;

import in.kamranali.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
