package com.sktelecom.jse.controller;

import javax.swing.JOptionPane;

import com.sktelecom.jse.domain.MemberBean;
import com.sktelecom.jse.domain.PhoneBean;
import com.sktelecom.jse.service.SktelecomService;
import com.sktelecom.jse.serviceImpl.SktelecomServiceImpl;

public class SktelecomController {
	public static void main(String[] args) {
		PhoneBean phone = null;
		MemberBean member = null;
		SktelecomService sktelecomService = new SktelecomServiceImpl();
		while(true) {
			switch(JOptionPane.showInputDialog(
					null,
					"[0] 종료\n"
					+ "[1] 개통\n"
					+ "[2] 회원리스트\n"
					+ "[3] 회원번호로조회\n"
					+ "[4] 이름으로조회\n"
					+ "[5] 번호이동\n"
					+ "[6] 회원탈퇴")) {
				case "0":
					JOptionPane.showMessageDialog(null, "안녕히가세요. 고객님");
					return;
				case "1":
					phone = new PhoneBean();
					member = new MemberBean();
					phone.setName(
							(JOptionPane
							.showInputDialog(
							"어느 폰으로 개통하시겠습니까?\n"
							+ "([1] galaxy, [2] iphone)").equals("1") ? 
							"galaxy" : "iphone"));
					member.setName(
							JOptionPane.showInputDialog(
							"개통자 이름을 입력해주세요"));
					JOptionPane.showMessageDialog(
							null, 
							sktelecomService
							.showMessage(member, phone));
					break;
				case "2":
					JOptionPane.showMessageDialog(
							null, sktelecomService.memberList());
					break;
				case "3":
					JOptionPane.showMessageDialog(null, sktelecomService
							.findByKey(JOptionPane
									.showInputDialog("회원번호 입력")));
					break;
				case "4":
					JOptionPane.showMessageDialog(null, 
							sktelecomService
							.findByName(JOptionPane
									.showInputDialog("회원이름 입력")));
					break;
				case "5":
					String key = JOptionPane
							.showInputDialog("회원번호 입력");
					sktelecomService.updatePhoneNumber(key);
					JOptionPane.showMessageDialog(
							null, 
							sktelecomService.findByKey(key));
					break;
				case "6":
					sktelecomService
					.deleteMember(
						JOptionPane
						.showInputDialog("회원번호 입력"));
					JOptionPane.showMessageDialog(null, 
							"탈퇴가 완료되었습니다");
					break;
				default:
					break;
			}
		}
	}
}
