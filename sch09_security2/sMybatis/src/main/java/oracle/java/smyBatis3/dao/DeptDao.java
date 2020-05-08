package oracle.java.smyBatis3.dao;
import java.util.List;
import java.util.Map;

import oracle.java.smyBatis3.model.Dept;
import oracle.java.smyBatis3.model.DeptVO;

public interface DeptDao {
	List<Dept> deptSelect();
	
	void insertDept(DeptVO deptVO);             // Procedure VO
	void SelListDept(Map<String,Object> map);  // Procedure Cursor

}