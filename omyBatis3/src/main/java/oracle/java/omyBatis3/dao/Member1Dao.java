package oracle.java.omyBatis3.dao;

import java.util.List;

import oracle.java.omyBatis3.model.Member1;

public interface Member1Dao {
	
	List<Member1> 	listMem(Member1 member1);
	int 			memCount(String id);

	
}
