package oracle.java.omyBatis3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.omyBatis3.model.Emp;
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

}
