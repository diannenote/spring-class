package oracle.java.omyBatis3.service;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.omyBatis3.dao.DeptDao;
import oracle.java.omyBatis3.dao.EmpDao;
import oracle.java.omyBatis3.dao.Member1Dao;
import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
import oracle.java.omyBatis3.model.Member1;
import oracle.java.omyBatis3.model.DeptVO;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	
		// ed->EmpDaoImpl 
	public List<Emp> list(Emp emp) {
		return ed.list(emp);
	}
	// ed->EmpDaoImpl 
	public int total() {
		return ed.total();
	}
	// ed->EmpDaoImpl 
	public Emp detail(int empno) {
		
		return ed.detail(empno);
	}
	


	// ed->EmpDaoImpl 
	public int update(Emp emp) {
		return ed.update(emp);
	}

	// ed->EmpDaoImpl 
	@Override
	public List<Emp> listManager() {
		// TODO Auto-generated method stub
		return ed.listManager();
	}		
	// dd->DeptDao 
	public List<Dept> select() {
		return dd.deptSelect();
	}

	public int insert(Emp emp) {
		return ed.insert(emp);
	}	

	// ed->EmpDaoImpl 
	public int delete(int empno) {
		// Business Rule Coding...
		return ed.delete(empno);
	}

	// ed->EmpDaoImpl 
	public List<EmpDept> listEmp(EmpDept empDept) {
		return ed.listEmp(empDept);
	}
	
	@Override
	public String deptName(int deptno) {
		// TODO Auto-generated method stub
		return ed.deptName(deptno);
	}

	// Procedure Call Test 
	@Override
	public void insertDept(DeptVO deptVO) {
		// TODO Auto-generated method stub
		 dd.insertDept(deptVO);
	}

	// Procedure Call Cursor Test 
	@Override
	public void SelListDept(Map<String, Object> map) {
		// TODO Auto-generated method stub
		dd.SelListDept(map);
	}

	
	// Member1 -> InterCeptor
	@Override
	public int memCount(String id) {
		// TODO Auto-generated method stub
		System.out.println("EmpServiceImpl memCount id ->"+id);
	    return md.memCount(id); 
	}

	// Member1 -> InterCeptor
	@Override
	public List<Member1> listMem(Member1 member1) {
		// TODO Auto-generated method stub
		System.out.println("EmpServiceImpl listMem ");
		return md.listMem(member1);
	}

	
	
	
	
}