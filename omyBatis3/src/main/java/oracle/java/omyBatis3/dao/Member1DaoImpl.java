package oracle.java.omyBatis3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.java.omyBatis3.model.Member1;

@Repository
public class Member1DaoImpl  implements Member1Dao {
	@Autowired
	private SqlSession session;
	@Override
	public int memCount(String id) {
		// Member1.xml->id(memCount), return int
		System.out.println("Member1DaoImpl id->"+id);
		return session.selectOne("memCount", id);
	}
	@Override
	public List<Member1> listMem(Member1 member1) {
		// TODO Auto-generated method stub
		System.out.println("Member1DaoImpl listMem ");
		return session.selectList("listMem", member1);
	}


}






