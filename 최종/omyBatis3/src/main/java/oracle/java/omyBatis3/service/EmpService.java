package oracle.java.omyBatis3.service;
import java.util.List;
import java.util.Map;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
import oracle.java.omyBatis3.model.Member1;
import oracle.java.omyBatis3.model.DeptVO;
//import oracle.java.omyBatis3.model.Member1;
public interface EmpService {
	List<Emp> list(Emp emp);
	int     total();
	Emp 	detail(int empno);
	int 	update(Emp emp);
	List<Emp> listManager();
	List<Dept> select();
	
	int 	insert(Emp emp);
	int 	delete(int empno);
	
	List<EmpDept> listEmp(EmpDept empDept);
	String  deptName(int deptno);

	void 	insertDept(DeptVO deptVO);
	void    SelListDept(Map<String,Object> map); 

	
	int     memCount(String id); 
	List<Member1> listMem(Member1 member1);
	
	
	
	
}