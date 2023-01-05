package com.oti.myuniversity.member.service;

import java.util.List;

import com.oti.myuniversity.common.Pager;
import com.oti.myuniversity.member.model.Member;

public interface IMemberService {
	Member selectMember(String memberId);
	void updateMember(Member member);
	List<Member> selectMemberList(Pager pager);
	int getTotalMemberCount();
}
