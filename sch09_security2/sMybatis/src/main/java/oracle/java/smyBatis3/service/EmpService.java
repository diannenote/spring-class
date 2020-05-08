package oracle.java.smyBatis3.service;
import java.util.List;
import java.util.Map;

import oracle.java.smyBatis3.model.Dept;
import oracle.java.smyBatis3.model.DeptVO;
import oracle.java.smyBatis3.model.Emp;
import oracle.java.smyBatis3.model.EmpDept;
import oracle.java.smyBatis3.model.Member1;
public interface EmpService {
	List<Emp> list(Emp emp);
	int     total();
	Emp 	detail(int empno);
	void    insertEmp();
	int 	update(Emp emp);
	List<Emp> listManager();
	List<Dept> select();
	
	int 	insert(Emp emp);
	int 	delete(int empno);
	List<EmpDept> listEmp(EmpDept empDept);
	String  deptName(int deptno);
	
	int     memCount(String id); 
	List<Member1> listMem(Member1 member1);
	
	void 	insertDept(DeptVO deptVO);
	void    SelListDept(Map<String,Object> map); 
	
//	int 	insert2(Crawling crawl);
//	// Member1
////	DeptVO 	insertDept(DeptVO deptVO);
	
	
	
	
	
	
	
	
}