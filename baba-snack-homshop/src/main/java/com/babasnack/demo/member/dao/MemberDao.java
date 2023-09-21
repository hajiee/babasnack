package com.babasnack.demo.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Member;

@Mapper
public interface MemberDao {
	@Insert("insert into member values(#{username},#{password},#{email},#{joinday},#{profile},#{role}")
	public Long save(Member member);
	
	@Select("select * from member where username=#{username} and rownum=1")
	public Member FindById(String username);
	@Select("select*from member where email=#{email} and rownum=1")
	public Member FindByEmail(String email);
	@Update("update member set email=#{email} where username=#{username}")
	  public Integer PsChangeEm(String email, String username);
	
	@Update("update member set PsPhoneNo=#{pno_tell} where username=#{username}")
	  public Integer PsChangePno(String PsPhoneNo, String username);
	
	@Delete("delete from member where username=#{username}")
	  public Integer PsWithdrawl(String username);
	
}
