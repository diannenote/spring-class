package oracle.java.omyBatis3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.omyBatis3.dao.DeptDao;
import oracle.java.omyBatis3.dao.EmpDao;
import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.Emp;


@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
	private EmpDao ed;
    @Autowired
	private DeptDao dd;
	
	
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


	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail empno->"+empno);
		return ed.detail(empno);
	}


	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl update getEmpno->"+emp.getEmpno());
		return ed.update(emp);
	}
	
	// ed->EmpDaoImpl 
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpServiceImpl listManager...");
		return ed.listManager();
	}		
	// dd->DeptDao 
	public List<Dept> select() {
		System.out.println("EmpServiceImpl select ....");
		return dd.deptSelect();
	}

	public int insert(Emp emp) {
		System.out.println("EmpServiceImpl insert ....");
		return ed.insert(emp);
	}	

}
