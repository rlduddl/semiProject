package com.semiProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.semiProject.vo.MemberVO;

@Mapper
public interface MemberMapper {
    List<MemberVO> selectList(MemberVO memberVO);

    MemberVO selectOne(MemberVO memberVO);

    void insert(MemberVO memberVO);

    void update(MemberVO memberVO);

    void delete(MemberVO memberVO);

    void memberDrop(Long idx);

    int checkUserID(String userID);

    int checkEmail(String email);

    String findID(String email);

    Long findPW(MemberVO memberVO);

    void updatePW(MemberVO memberVO);

    void updateInfo(MemberVO memberVO);

}
