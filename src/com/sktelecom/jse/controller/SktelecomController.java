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
					+ "[2] 회원리스트\n")) {
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
				default:
					break;
			}
		}
	}
}
