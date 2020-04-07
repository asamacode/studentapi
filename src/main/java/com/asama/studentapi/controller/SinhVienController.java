package com.asama.studentapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asama.studentapi.model.bean.SinhVien;
import com.asama.studentapi.model.service.SinhVienService;

@RestController
@RequestMapping(value = "/student/api")
public class SinhVienController {

	@Autowired
	SinhVienService service;

	@RequestMapping(value = "/getall")
	public List<SinhVien> getAllSinhVien() {
		return service.getAllDataSinhViens();
	}

	@RequestMapping(value = "/getsv")
	public Optional<SinhVien> getSingleSinhVien(@RequestParam(name = "id") Integer id) {
		return service.getSinhVien(id);
	}

	@RequestMapping(value = "/getsvbyname")
	public List<SinhVien> getSinhVienByName(@RequestParam(value = "name") String name) {
		return service.gerAllSinhVienByName(name);
	}

	@RequestMapping(value = "/insert")
	public boolean insertOneSV(@RequestBody SinhVien sinhVien) {
		service.insertOneSinhVien(sinhVien);
		return true;
	}

	@RequestMapping(value = "/insertmany")
	public boolean inserManySV(@RequestBody List<SinhVien> sinhViens) {
		service.insertManySinhVien(sinhViens);
		return true;
	}

	@RequestMapping(value = "/update")
	public boolean updateSV(@RequestBody SinhVien sinhVien) {
		service.updateSV(sinhVien);
		return true;
	}

	@RequestMapping(value = "/check")
	public boolean checkSinhVienExists(@RequestBody SinhVien sinhVien) {
		return service.checkSinhVienExists(sinhVien.getId());
	}

	@RequestMapping(value = "/delete")
	public boolean deleteOneSV(@RequestBody SinhVien sinhVien) {
		service.deleteOneSinhVien(sinhVien.getId());
		return true;
	}

	@RequestMapping(value = "/deletebyname")
	public boolean deleteByName(@RequestBody SinhVien sinhVien) {
		service.deleteByName(sinhVien.getName());
		return true;
	}

	@RequestMapping(value = "/getallsort")
	public List<SinhVien> getAllSVSortDesc() {
		return service.getListSVSortById();
	}
	
	@RequestMapping(value = "/allsv")
	public Page<SinhVien> get10SV(@PageableDefault(page = 1) @RequestParam(name = "page") Integer page) {
		return service.get10SinhVienPerPage(page);
	}
	
	@RequestMapping(value = "/findbylastname")
	public List<SinhVien> getSVByLastName(@RequestParam(name = "lastname") String lastName) {
		return service.getAllSinhVienByLastName(lastName);
	}
	
	@RequestMapping(value = "/deletebynameandaddress")
	public boolean deleteByNameAndAddress(@RequestBody SinhVien sinhVien) {
		service.deleteAllSVByNameAndAddress(sinhVien);
		return true;
	}
	
	@RequestMapping(value = "/findbyaddress") 
	public List<SinhVien> getSinhVienByAddress(@RequestParam(name = "address") String address) {
		return service.getAllSVByAddressAndSortAscByName(address);
	}
}
