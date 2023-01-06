package com.oti.myuniversity.member.repository;

import java.util.List;

import com.oti.myuniversity.common.Pager;
import com.oti.myuniversity.member.model.Member;

public interface IMemberRepository {
	Member selectMember(String memberId);
	void updateMember(Member member);
	List<Member> selectMemberList(Pager page);
	int getTotalMemberCount();
}
