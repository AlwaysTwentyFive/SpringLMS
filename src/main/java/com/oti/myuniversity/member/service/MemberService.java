package com.oti.myuniversity.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oti.myuniversity.member.model.Member;
import com.oti.myuniversity.member.repository.IMemberRepository;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	IMemberRepository memberRepository;

	@Override
	public Member selectMember(String memberId) {
		return memberRepository.selectMember(memberId);
	}

}
