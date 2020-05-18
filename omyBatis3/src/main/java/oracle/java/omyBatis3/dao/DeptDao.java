package oracle.java.omyBatis3.dao;

import java.util.HashMap;
import java.util.List;

import oracle.java.omyBatis3.model.Dept;
import oracle.java.omyBatis3.model.DeptVo;

public interface DeptDao {

	List<Dept> 		deptSelect();
	void 			insertDept(DeptVo deptVO);
	void 			SelListDept(HashMap<String, Object> map);
	
}
