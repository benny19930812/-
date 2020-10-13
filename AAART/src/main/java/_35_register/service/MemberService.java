package _35_register.service;

import _35_register.model.MemberBean;

public interface MemberService {
	boolean idExists(String id);
	int saveMember(MemberBean mb);
	MemberBean queryMember(String id);
	public MemberBean checkIDPassword(String userId, String password) ;
}
