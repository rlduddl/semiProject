package com.semiProject.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semiProject.mapper.MemberMapper;
import com.semiProject.payload.request.ChangePwRequest;
import com.semiProject.payload.request.JoinRequest;
import com.semiProject.service.CrudService; // 수정된 import
import com.semiProject.util.StringUtil;
import com.semiProject.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements CrudService<MemberVO> { // MemberVO에 대한 CRUD 구현
    @Autowired
    private MemberMapper mapper;

    @Override
    public List<MemberVO> selectList(MemberVO e) {
        return mapper.selectList(e);
    }

    @Override
    public MemberVO selectOne(MemberVO e) {
        return mapper.selectOne(e);
    }
    
    public MemberVO selectOne(String userID, String password) {
        MemberVO vo = new MemberVO();
        vo.setUserID(userID);
        vo.setPassword(password);
        return mapper.selectOne(vo);
    }

    @Override
    public void insert(MemberVO e) {
        mapper.insert(e);
    }

    @Override
    public void update(MemberVO e) {
        mapper.update(e);
    }

    @Override
    public void delete(MemberVO e) {
        mapper.delete(e);
    }

    public void memberDrop(Long idx) {
        mapper.memberDrop(idx);
    }
    
    public HashMap<String, Object> checkUserID(String userID) {
        int cnt = mapper.checkUserID(userID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("isExist", cnt != 0);
        return map;
    }
    
    public HashMap<String, Object> checkEmail(String email) {
        int cnt = mapper.checkEmail(email);
        HashMap<String, Object> map = new HashMap<>();
        map.put("isExist", cnt != 0);
        return map;
    }
    
    public HashMap<String, Object> memberJoin(JoinRequest joinRequest) {
        HashMap<String, Object> map = new HashMap<>();
        
        HashMap<String, Object> idMap = this.checkUserID(joinRequest.getUserID());
        boolean idExist = (boolean) idMap.get("isExist");
        if (idExist) {
            map.put("result", false);
            map.put("message", "아이디가 사용중입니다.");
            return map;
        }
        
        HashMap<String, Object> emailMap = this.checkEmail(joinRequest.getEmail());
        boolean emailExist = (boolean) emailMap.get("isExist");
        if (emailExist) {
            map.put("result", false);
            map.put("message", "이메일이 사용중입니다.");
            return map;
        }
        
        String pw1 = joinRequest.getPassword();
        String pw2 = joinRequest.getPassword2();
        if (!pw1.equals(pw2)) {
            map.put("result", false);
            map.put("message", "비밀번호가 일치하지 않습니다.");
            return map;
        }
        if (pw1.length() < 4) {
            map.put("result", false);
            map.put("message", "비밀번호는 4글자 이상 입력하세요.");
            return map;
        }
        
        String username = joinRequest.getUsername();
        if (username.length() < 2) {
            map.put("result", false);
            map.put("message", "이름은 2글자 이상 입력하세요.");
            return map;
        }
        
        MemberVO memberVO = MemberVO.builder()
                .email(joinRequest.getEmail())
                .userID(joinRequest.getUserID())
                .password(pw1)
                .username(username)
                .build();
        
        this.insert(memberVO);
        
        map.put("result", true);
        map.put("message", "가입이 완료되었습니다.");
        
        return map;
    }
    
    public HashMap<String, Object> findID(String email) {
        HashMap<String, Object> map = new HashMap<>();
        String userID = mapper.findID(email);
        
        map.put("result", userID != null);
        map.put("message", userID == null ? "찾으시는 아이디가 없습니다." : "찾으시는 아이디는 " + userID + "입니다.");
        return map;
    }

    public HashMap<String, Object> changePW(ChangePwRequest changePwRequest) {
        MemberVO memberVO = MemberVO.builder()
                .email(changePwRequest.getEmail())
                .userID(changePwRequest.getUserID())
                .build();
        Long idx = mapper.findPW(memberVO);
        
        HashMap<String, Object> map = new HashMap<>();
        
        if (idx == null) {
            map.put("result", false);
            map.put("message", "계정을 찾을 수 없습니다.");
            return map;
        }
        
        String randomPw = StringUtil.generateRandomString(10);
        
        memberVO = MemberVO.builder()
                .password(randomPw)
                .idx(idx)
                .build();
        mapper.updatePW(memberVO);
        
        map.put("result", true);
        map.put("message", "임시 비밀번호는 " + randomPw + "입니다.");
        
        return map;
    }

    public boolean updateInfo(HttpServletRequest request, MemberVO memberVO) {
        HttpSession session = request.getSession();
        MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
        
        if (userInfo == null) {
            return false;
        }
        
        if (!memberVO.getIdx().equals(userInfo.getIdx())) {
            return false;
        }
        
        if (!memberVO.getUserID().equals(userInfo.getUserID())) {
            return false;
        }
        
        if (!memberVO.getPassword().equals("")) {
            memberVO.setPassword("");
        }
        
        mapper.updateInfo(memberVO);
        
        return true;
    }
}
