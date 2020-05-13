package oracle.java.omyBatis3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.omyBatis3.dao.EmpDao;
import oracle.java.omyBatis3.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
	private EmpDao ed;
	
	
	@Override
	public List<Emp> list(Emp emp) {
		// TODO Auto-generated method stub
		return ed.list(emp);
	}


	@Override
	public int total() {
		System.out.println("EmpServiceImpl total...");

		return ed.total();
	}

}
