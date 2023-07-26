package com.groo.bear.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.payment.service.PaymentService;
import com.groo.bear.payment.service.PaymentVO;

//김영환 1.전자결재
@Controller
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	//결재자 사원 받아와서 모달창에 뿌려주는 controller
	@GetMapping("pay/paymentEmp")
	public String payEmpList(Model model,HttpServletRequest request) {
		model.addAttribute("payEmpList",paymentService.payEmpList());
		HttpSession session = request.getSession();
		System.out.println("session = "+session);
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		System.out.println("기안자 : "+paymentService.payEmpInfo(id));
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/paymentEmp";
	}
	//참조자 사원 받아와서 모달창에 뿌려주는 controller
	@GetMapping("pay/referrerEmp")
	public String referrerEmp(Model model, HttpServletRequest request) {
		model.addAttribute("payEmpList",paymentService.payEmpList());
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/referrerEmp";
	}
	//결재문서 페이지
	@GetMapping("pay/paymentDoc")
	public String paymentDocForm(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		int payNo = paymentService.paymentNo();
		System.out.println(payNo);
		model.addAttribute("paymentNo",paymentService.paymentNo());
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/paymentDoc";
	}
	//운행일지 작성
	@PostMapping("paymentDocLog")
	@ResponseBody
	public String paymentDocLog(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocLog = "+payVO);
		paymentService.paymentLogBook(payVO);
		paymentService.logDataInsert(payVO);
		return "성공";
	}
	//연차계 작성
	@PostMapping("paymentDocOff")
	@ResponseBody
	public String paymentDocOff(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocOff = "+payVO);
		paymentService.paymentLogBook(payVO);
		paymentService.offDataInsert(payVO);
		return "성공";
	}
	//품의서 작성
	@PostMapping("paymentDocRobin")
	@ResponseBody
	public String paymentDocRobin(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocOff = "+payVO);
		paymentService.paymentLogBook(payVO);
		paymentService.robinDataInsert(payVO);
		return "성공";
	}
	//전자서명 페이지
	@GetMapping("pay/paySign")
	public String paySignForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		EmpVO empVO = new EmpVO();
		empVO = paymentService.payEmpInfo(id);
		System.out.println("payEmp = "+empVO);
		model.addAttribute("userInfo",empVO);
		return "pay/paySign";
	}
	//개인전자서명 등록
	@PostMapping("mypaySign")
	@ResponseBody
	public String mypaySign(@RequestBody EmpVO empVO) {
		System.out.println("ajax에서 다시보낸 정보"+empVO);
		paymentService.insertSignImg(empVO);
		return "성공";
	}
	//전자결재 확인하는 페이지
	@GetMapping("pay/payPreferences")
	public String payPreferences(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		EmpVO empVO = new EmpVO();
		empVO = paymentService.payEmpInfo(id);
		System.out.println("payEmp = "+empVO);
		model.addAttribute("userInfo",empVO);
		System.out.println("EmpNo = "+empVO.getEmpNo());
		FilesVO fileVO = new FilesVO();
		fileVO = paymentService.searchSignImg(empVO.getEmpNo());
		System.out.println("search = "+fileVO);
		model.addAttribute("userSignInfo",fileVO);
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/payPreferences";
	}
	//개인 서명 제거
	@PostMapping("signdelete")
	@ResponseBody
	public int signdelete(@RequestBody int signNo) {
		System.out.println("signNo! = "+signNo);
		int result = paymentService.deleteSignImg((int) signNo);
		return result;
	}
	//운행일지 파일등록
	@PostMapping("logInsert")
	@ResponseBody
	public int logInsert(@RequestBody FilesVO vo) {
		int result = paymentService.logInsert(vo);
		return result;
	}
	
	//결재 진행중인 문서 조회
	
	//결재 완료(반려)된 문서 조회
	
	//참조된 문서 조회
	
	
}
