package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Standard;
import com.example.demo.repository.StandardRepository;

@Service
public class StandardService {

	@Autowired
	StandardRepository stdRepo;
	
	public List<Standard> getAllStandards() {
		List<Standard> stds = new ArrayList<>();
		stdRepo.findAll().forEach(stds::add);
		return stds;
	}

	public Standard addAndUpdate(Standard std) {
		return stdRepo.save(std);
	}

	public Standard getStandard(Long stdId) {
		return stdRepo.findByStdId(stdId);
	}

	public void deleteStandard(Long stdId) {
		stdRepo.deleteById(stdId);
		
	}
	
	

}
