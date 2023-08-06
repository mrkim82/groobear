package com.groo.bear.mail.mapper;

import java.util.List;

import com.groo.bear.mail.service.MailVO;
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
	public int deleteMail(MailVO mailVO);
	//메일 완전삭제(delete)
	public int realDeleteMail(MailVO mailVO);
	//메일 상세조회
	public MailVO mailInfo(MailVO mailVO);
	// 보낸메일 총 갯수
	public int countSendMail(MailVO mailVO);
	// 받은메일 총 갯수
	public int countReceiveMail(MailVO mailVO);
	// 지운메일 총 갯수
	public int countDeleteMail(MailVO mailVO);
	//메일번호 받아오는 쿼리문
	public int mailNo();
	//수신자가 메일상세조회로 읽을경우
	public int mailCheck(int mailNo);
	//personnel에 추가값 넣기
	public int insertPersonnel(MailVO mailVO);
	//personnel 맥스번호
	public int personnelNo();
	//이메일로 유저아이디 받아오기
	public String userIdGet(String email);
	//서버에서 받아온 이메일 넣는구문
	public int serverGetInsertMail(MailVO mailVO);
	//서버에서 받아온 메일 받은메일함
	public List<MailVO> getMailList(Criteria cri, MailVO mailVO);
	//서버에서 받아온 메일 상세조회
	public MailVO getMailInfo(int mailNo);
	//서버에서 받아온 메일 상세조회시 읽은메일로 변경
	public int getMailInfoUpdate(MailVO mailVO);
	//서버에서 받아온 메일 보낸메일함
	public List<MailVO> getMailSend(Criteria cri, MailVO mailVO);
	//서버에서 받아온 메일 지운메일함
	public List<MailVO> getMaildelete(Criteria cri, MailVO mailVO);
	//메일 삭제(지운메일함으로 보내는 업데이트문)
	public int getMailType1Del(MailVO mailVO);
	public int getMailType2Del(MailVO mailVO);
	public int getMailType3Del(MailVO mailVO);
	public int getMailType4Del(MailVO mailVO);
	public int getMailType5Del(MailVO mailVO);
	//보낸메일함
	public List<MailVO> sendMailSearch(Criteria cri,MailVO mailVO);
}
