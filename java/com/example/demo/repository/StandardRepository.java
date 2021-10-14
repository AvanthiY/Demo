package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Standard;

public interface StandardRepository extends JpaRepository<Standard, Long>{

	public Standard findByStdId(Long id);
}
