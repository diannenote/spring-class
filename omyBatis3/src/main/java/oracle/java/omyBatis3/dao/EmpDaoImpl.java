package oracle.java.omyBatis3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
    private SqlSession session;
	@Override
	public List<Emp> list(Emp emp) {
		// TODO Auto-generated method stub
		return session.selectList("listAll", emp);
	}
	@Override
	public int total() {
		System.out.println("EmpDaoImpl  total ...");
		return session.selectOne("total");
	}
	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl detail empno->" + empno);
		return session.selectOne("detail", empno);
	}
	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl update empno->" + emp.getEmpno());
		
		return session.update("TKempUpdate", emp);
	}
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpDaoImpl update listManager...");
		
		return session.selectList("selectManager");
	}
	@Override
	public int insert(Emp emp) {
		System.out.println("EmpDaoImpl insert empno->" + emp.getEmpno());
		
		return session.update("TKempinsert", emp);
	}
	@Override
	public int delete(int empno) {
		System.out.println("EmpDaoImpl delete empno->" + empno);
		return session.delete("delete", empno);
	}
	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpDaoImpl listEmp empDept->" + empDept);
		return session.selectList("TKlistemp", empDept);
	}
	@Override
	public String deptName(int deptno) {
		// TODO Auto-generated method stub
		return session.selectOne("deptName", deptno);
	}
	
	

}
