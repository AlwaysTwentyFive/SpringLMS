package com.oti.myuniversity.member.repository;

import com.oti.myuniversity.member.model.Member;

public interface IMemberRepository {
	Member selectMember(String memberId);
}
