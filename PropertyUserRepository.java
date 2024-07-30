package com.Airbnb.reopsitory;

import com.Airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {


    Optional<PropertyUser> findByUserName(String userName);
}