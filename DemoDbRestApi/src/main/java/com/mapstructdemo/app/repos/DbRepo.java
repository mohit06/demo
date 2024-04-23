package com.mapstructdemo.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapstructdemo.app.entities.Student;

public interface DbRepo extends JpaRepository<Student,Long>{

}
