package org.java.spring.db.repo;

import org.java.spring.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer> {

}
