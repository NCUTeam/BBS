package com.homework.bbs.bbsdemo.dao;

import com.homework.bbs.bbsdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;


public interface UserDao extends JpaRepository<User, BigInteger> {

//    @Query("select password from User t where t.username=:id")
//    public String findByName(@Param("username") String username);

    public List<User> findByUsername(String username);

}
