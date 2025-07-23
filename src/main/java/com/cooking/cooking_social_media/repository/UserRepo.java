package com.cooking.cooking_social_media.repository;

import com.cooking.cooking_social_media.model.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUserName(String userName);

    public User findByEmail(String email);
}
