package com.oti.myuniversity.domain.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.component.Pager;
import com.oti.myuniversity.domain.member.model.Member;
import com.oti.myuniversity.domain.member.repository.IMemberRepository;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	IMemberRepository memberRepository;

	@Override
	public Member selectMember(String memberId) {
		return memberRepository.selectMember(memberId);
	}

	@Override
	public void updateMember(Member member) {
		memberRepository.updateMember(member);
	}

	@Override
	public List<Member> selectMemberList(Pager pager) {
		return memberRepository.selectMemberList(pager);
	}

	@Override
	public int getTotalMemberCount() {
		return memberRepository.getTotalMemberCount();
	}

}
