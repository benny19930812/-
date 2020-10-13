package _35_register.service.impl;

import _35_register.dao.MemberDao;
import _35_register.dao.impl.MemberDaoImpl_Jdbc;
import _35_register.model.MemberBean;
import _35_register.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao  dao ;
	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl_Jdbc();
	}

	@Override
	public int saveMember(MemberBean mb) {
		return dao.saveMember(mb);
	}

	@Override
	public boolean idExists(String id) {
		return dao.idExists(id);
	}

	@Override
	public MemberBean queryMember(String id) {
		return dao.queryMember(id);
	}

	public MemberBean checkIDPassword(String userId, String password) {
		MemberBean mb = dao.checkIDPassword(userId, password);
		return mb;
	}
}
