package com.oti.myuniversity.domain.member.service;

import java.util.List;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.member.model.Member;

public interface IMemberService {
	Member selectMember(String memberId);
	void updateMember(Member member);
	List<Member> selectMemberList(Pager pager);
	int getTotalMemberCount();
}
