package oracle.java.omyBatis3.service;

import java.util.HashMap;
import java.util.List;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.DeptVo;
import oracle.java.omyBatis3.model.Emp;
import oracle.java.omyBatis3.model.EmpDept;
import oracle.java.omyBatis3.model.Member1;

public interface EmpService {
    List<Emp>  		list(Emp emp);
    int         	total();
	Emp 			detail(int empno);
	int 			update(Emp emp);
	List<Emp>   	listManager();
	List<Dept>  	select();
	
	int 			insert(Emp emp);
	int       	 	delete(int empno);
	
	List<EmpDept> 	listEmp(EmpDept empDept);
	String 			deptName(int deptno);
	
	void 			insertDdept(DeptVo deptVO);
	void 			SelListDept(HashMap<String, Object> map);
	
	int 			memCount(String id);
	List<Member1> 	listMem(Member1 member1);
	
	
	
	
	
}
