package oracle.java.omyBatis3.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;

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
//
	@Override
	public String deptName(int deptNo) {
		// TODO Auto-generated method stub
		return session.selectOne("TKdeptName",deptNo);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}