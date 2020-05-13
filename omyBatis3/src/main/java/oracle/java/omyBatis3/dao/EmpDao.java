package oracle.java.omyBatis3.dao;

import java.util.List;

import oracle.java.omyBatis3.model.Emp;

public interface EmpDao {
   List<Emp> list(Emp emp);
   int  total();
}
