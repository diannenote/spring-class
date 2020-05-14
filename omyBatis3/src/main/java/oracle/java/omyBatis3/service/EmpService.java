package oracle.java.omyBatis3.service;

import java.util.List;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.Emp;

public interface EmpService {
    List<Emp>   list(Emp emp);
    int         total();
	Emp 		detail(int empno);
	int 		update(Emp emp);
	List<Emp>   listManager();
	List<Dept>  select();
	int 		insert(Emp emp);
	
}
