package com.oti.myuniversity.domain.member.repository;

import java.util.List;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.member.model.Member;

public interface IMemberRepository {
	Member selectMember(String memberId);
	void updateMember(Member member);
	List<Member> selectMemberList(Pager page);
	int getTotalMemberCount();
}
