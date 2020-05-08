package oracle.java.smyBatis3.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.smyBatis3.model.Emp;
import oracle.java.smyBatis3.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao{
	@Autowired
	private SqlSession session;
	public List<Emp> list(Emp emp) {
		// emp.xml->id(listALL), parameterType(emp), return List
		return session.selectList("listAll", emp);
		
	}
	public int total() {
		// emp.xml->id(total), return int
		return session.selectOne("total");
	}
	public void insertEmp() {
		Emp emp = new Emp();
		for (int i = 3001; i <= 3120;i++) {
			emp.setEmpno(i);
			emp.setEname("상오"+i);
			emp.setJob("영업");
  			emp.setMgr(7369);
			emp.setHiredate("2018-07-16");
			emp.setSal(3000);
			emp.setComm(500);
			emp.setDeptno(20);
			session.insert("insert",emp);
		}
	}
	 
	public Emp detail(int empno) {
		return session.selectOne("detail",empno);
	}

	public int update(Emp emp) {
		return session.update("TKempUpdate",emp);
	}


	@Override
	public List<Emp> listManager() {
		return session.selectList("selectManager");
	}	

	public int insert(Emp emp) {
		return session.insert("insert",emp);
	}
	
	public int delete(int empno) {
		return session.delete("delete",empno);
	}

	// EmpDept.xml->id(listEmp), parameterType(empDept), return List
	public List<EmpDept> listEmp(EmpDept empDept) {
		return session.selectList("TKlistEmp", empDept);
	}

	@Override
	public String deptName(int deptNo) {
		// TODO Auto-generated method stub
		return session.selectOne("TKdeptName",deptNo);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}