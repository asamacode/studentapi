package com.asama.studentapi.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.asama.studentapi.model.bean.SinhVien;
import com.asama.studentapi.model.dao.SinhVienDAO;

@Service
public class SinhVienService {

	@Autowired
	SinhVienDAO sinhVienDAO;

	public List<SinhVien> getAllDataSinhViens() {
		return sinhVienDAO.findAll();
	}

	public Optional<SinhVien> getSinhVien(Integer id) {
		return sinhVienDAO.findById(id);
	}

	public List<SinhVien> gerAllSinhVienByName(String name) {
		SinhVien sinhVien = new SinhVien();
		sinhVien.setName(name);

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id").withIgnorePaths("address");

		return sinhVienDAO.findAll(Example.of(sinhVien, matcher));
	}
	
	public void insertOneSinhVien(SinhVien sinhVien) {
		sinhVienDAO.save(sinhVien);
	}
	
	public void insertManySinhVien(List<SinhVien> sinhViens) {
		sinhVienDAO.saveAll(sinhViens);
	}
	
	public void updateSV(SinhVien sinhVien) {
		sinhVienDAO.save(sinhVien);
	}
}
