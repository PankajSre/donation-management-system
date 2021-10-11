package com.iiht.training.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.ngo.entity.DonationRequestEntity;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequestEntity, Long>{

}
