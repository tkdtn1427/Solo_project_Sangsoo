package com.codestates.member.repository;

import com.codestates.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM MEMBER WHERE company_location = :company_location",nativeQuery = true)
    Optional<List<Member>> findByCompany_Location(String company_location);

    @Query(value = "SELECT * FROM MEMBER WHERE company_type = :company_type", nativeQuery = true)
    Optional<List<Member>> findByCompany_Type(String company_type);
}
