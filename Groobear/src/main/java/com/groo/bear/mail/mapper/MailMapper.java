package com.groo.bear.mail.mapper;

import java.util.List;

import com.groo.bear.mail.service.MailVO;
import com.groo.bear.mail.service.PagingVO;
import com.groo.bear.paging.Criteria;

public interface MailMapper {
	public int sendMail(MailVO mailVO);
	//메일발송 (sendMail)
	//메일전체조회
	public List<MailVO> mailAllList(MailVO mailVO);
	//메일 전체조회 발송 후 update
	public int mailSetUpdate(int mailNo);
	//받은메일함 조회
	public List<MailVO> receiveMail(String email);
	//보낸메일함 조회
	public List<MailVO> sendingMail(String email);
	//지운메일함 조회
	public List<MailVO> deletedMail(Criteria cri, MailVO mailVO);
	//메일삭제(update)
	public int deleteMail(int mailNo);
	//메일 완전삭제(delete)
	public int realDeleteMail(int mailNo);
	//메일 상세조회
	public MailVO mailInfo(int mailNo);
	// 보낸메일 총 갯수
	public int countSendMail(String sender);
	// 받은메일 총 갯수
	public int countReceiveMail(String receiver, String referrer);
	// 지운메일 총 갯수
	public int countDeleteMail(MailVO mailVO);
	// 페이징 처리 보낸메일
	public List<MailVO> sendMailSearch(PagingVO vo);
	// 페이징 처리 받은메일
	public List<MailVO> receiveMailSearch(PagingVO vo);
	// 페이징 처리 지운메일
	public List<MailVO> deleteMailSearch(PagingVO vo);
}
