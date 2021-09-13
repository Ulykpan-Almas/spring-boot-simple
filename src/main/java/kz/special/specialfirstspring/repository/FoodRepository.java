package kz.special.specialfirstspring.repository;

import kz.special.specialfirstspring.entites.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional


public interface FoodRepository extends JpaRepository<Foods,Long> {


    List<Foods> findAllByPriceGreaterThanEqualAndWeightGreaterThanEqual(int price , double weight);

    Foods findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(Long id , int price , double weight);

}
