package com.iiht.training.ngo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.ngo.entity.DonationEntity;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
	List<DonationEntity> getByDonarId(Long donarId);

	List<DonationEntity> getByNgoId(Long ngoId);
}
