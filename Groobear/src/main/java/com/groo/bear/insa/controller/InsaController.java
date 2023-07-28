package com.groo.bear.insa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.insa.service.InsaService;
import com.groo.bear.mypage.service.UserService;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.ProPostTaskService;

@Controller
public class InsaController {
	
	@Autowired
	ProPostTaskService pPTService;
	
	@Autowired
	ProPostSchService proPostService;
	
	@Autowired
	InsaService insaService;
	
	@Autowired
	UserService userService;
	
	//회원조회 전체 리스트
	@GetMapping("/empInfo")
	public String getEmpInfo(Criteria cri, Model model, EmpVO vo) {
		int listCnt = insaService.EmpListCnt(cri, vo);

		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(listCnt);
	
		model.addAttribute("empList", insaService.selectEmpList(cri, vo));
		model.addAttribute("paging", paging);
		
		return "insa/empList";
	}
	
	// 회원정보 조회(사번을 이용한 회원정보 조회)
	@GetMapping("empDetailInfo")
	public String getEmpDetailInfo(@RequestParam String id, Model model) {
		model.addAttribute("profImg", userService.checkMyProf(id));
		model.addAttribute("empInfo", insaService.empDetailInfo(id));
		return "insa/empDetail";
	}
	
	// 회원정보 업데이트 폼으로 이동
	@GetMapping("updateEmp")
	public String updateEmpInfoForm(EmpVO vo, Model model) {
		model.addAttribute("profImg", userService.checkMyProf(vo.getId()));
		model.addAttribute("empInfo", insaService.empDetailInfo(vo.getId()));
		model.addAttribute("deptList", insaService.getDept());
		return "insa/empUpdate";
	}
	
	// 회원정보 업데이트
	@PostMapping("updateEmp")
	public String updateEmpInfo(EmpVO vo) {
		insaService.usersUpdate(vo);
		insaService.userInfoUpdate(vo);
		return "redirect:empDetailInfo?id="+vo.getId();
	}
	
	// 회원정보 삭제
	@ResponseBody
	@PostMapping("deleteEmp")
	public String deleteEmpInfo(@RequestBody EmpVO vo) {
		pPTService.deleteWorkPerson(vo.getId());
		proPostService.deleteMemberSchParti(vo.getId());
		if(insaService.usersDelete(vo.getEmpNo()) > 0) {
			return "success";
		} else {
			return "fail";
		}	
	}
	
	// 인사관리 전체리스트
	@GetMapping("/userInfoList")
	public String getUserInfoList(Criteria cri, Model model, EmpVO vo) {
		cri.setPerPageNum(5);
		int listCnt = insaService.userInfoListCnt(cri, vo);

		Paging paging = new Paging();
		
        paging.setCri(cri);
        paging.setTotalCount(listCnt);
	
		model.addAttribute("empList", insaService.userInfoList(cri, vo));
		model.addAttribute("paging", paging);
		model.addAttribute("deptList", insaService.getDept());
		
		return "insa/userInfoList";
	}
	
	// 인사정보 삭제
	@ResponseBody
	@PostMapping("delUserInfo")
	public String deleteUserInfo(@RequestBody List<EmpVO> vo) {	
		
		if(insaService.userInfoDel(vo) > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 인사정보 등록
	@ResponseBody
	@PostMapping("insertUserInfo")
	public String insertUserInfo(@RequestBody EmpVO vo) {
		if(insaService.checkPno(vo.getPno())>0) {
			return "has";
		}else{
			if(insaService.userInfoAdd(vo) > 0) {
				return "success";
			}else {
				return "fail";
			}
		}
	}
	
	// 인사정보 등록
	@ResponseBody
	@PostMapping("updateUserInfo")
	public String updateUserInfo(@RequestBody EmpVO vo) {
		if(insaService.checkPno(vo.getPno())>0) {
			return "has";
		}else{
			if(insaService.updateUserInfo(vo) > 0) {
				return "success";
			}else {
				return "fail";
			}
		}
	}
}
