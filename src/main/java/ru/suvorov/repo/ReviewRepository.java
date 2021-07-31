package ru.suvorov.repo;

import org.springframework.data.repository.CrudRepository;
import ru.suvorov.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
