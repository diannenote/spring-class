package oracle.java.smyBatis3.dao;
import java.util.List;

import oracle.java.smyBatis3.model.Emp;
import oracle.java.smyBatis3.model.EmpDept;

public interface EmpDao {
	List<Emp> list(Emp emp);
	int total();
   void insertEmp();
   Emp detail(int empno);
   int update(Emp emp);   
	List<Emp> listManager();
	int insert(Emp emp);
	int delete(int empno);
	List<EmpDept> listEmp(EmpDept empDept);
	
	String	deptName(int deptNo);


}