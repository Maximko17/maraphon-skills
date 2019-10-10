package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Charity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CharityRepository extends CrudRepository<Charity, Long> {

    @Query("select sum(sponsorSum) from Charity")
    Integer totalCharitySum();

    List<Charity> findAllByOrderByName();

    List<Charity> findAllByOrderBySponsorSumDesc();
}
