package com.mapstructdemo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mapstructdemo.app.entities.Student;
import com.mapstructdemo.app.repos.DbRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadDataIntoDB {

	private final DbRepo dbRepo;
	
	private int batch = 1000;
	private int start = 0;
	
	public void saveData() {
		while(start < 100000) {
		    List<Student> student = getNextStudentBatch(start);
		    start+=batch;
		    dbRepo.saveAll(student);
		}
	}
	
	public List<Student> fetchAll() {
		return dbRepo.findAll();
		
	}
	
	private List<Student> getNextStudentBatch(int start){
		 List<Student> student = new ArrayList<Student>();
		for(int i=start;i<start+batch;i++) {
			Student st = Student.builder().name("NAME_"+i).rollNo(i+1).standard((i+1)%10).build();
			student.add(st);
		}
		return student;
	}
}
