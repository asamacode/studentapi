package com.asama.studentapi.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asama.studentapi.model.bean.SinhVien;

@Transactional

public interface SinhVienDAO extends JpaRepository<SinhVien, Integer>{

}
