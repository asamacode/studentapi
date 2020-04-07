package com.asama.studentapi.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("address");

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

	public boolean checkSinhVienExists(Integer id) {
		return sinhVienDAO.existsById(id);
	}

	public void deleteOneSinhVien(Integer id) {
		sinhVienDAO.deleteById(id);
	}

	public void deleteByName(String name) {
		SinhVien sinhVien = new SinhVien();
		sinhVien.setName(name);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("address");

		List<SinhVien> sinhViens = sinhVienDAO.findAll(Example.of(sinhVien, matcher));

		sinhVienDAO.deleteAll(sinhViens);
	}
	
	public List<SinhVien> getListSVSortById() {
		return sinhVienDAO.findAll(Sort.by("id").descending());
	}
	
	public Page<SinhVien> get10SinhVienPerPage(Integer page) {
		return sinhVienDAO.findAll(PageRequest.of(page, 10));
	}
	
	public List<SinhVien> getAllSinhVienByLastName(String lastName) {
		SinhVien sinhVien = new SinhVien();
		sinhVien.setName(lastName);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withIgnorePaths("address")
				.withMatcher("name", match -> match.endsWith());
		
		return sinhVienDAO.findAll(Example.of(sinhVien, matcher));
	}
	
	public void deleteAllSVByNameAndAddress(SinhVien sinhVien) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id");
		List<SinhVien> sinhViens = sinhVienDAO.findAll(Example.of(sinhVien, matcher));
		sinhVienDAO.deleteAll(sinhViens);		
	}
	
	public List<SinhVien> getAllSVByAddressAndSortAscByName(String address) {
		SinhVien sinhVien = new SinhVien();
		sinhVien.setAddress(address);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withIgnorePaths("name");
		
		return sinhVienDAO.findAll(Example.of(sinhVien, matcher), Sort.by("name").ascending());
	}
}
