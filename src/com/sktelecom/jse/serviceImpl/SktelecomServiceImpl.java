package com.sktelecom.jse.serviceImpl;

import com.sktelecom.jse.domain.MemberBean;
import com.sktelecom.jse.domain.PhoneBean;
import com.sktelecom.jse.service.SktelecomService;

public class SktelecomServiceImpl implements SktelecomService{
	MemberBean[] members;
	PhoneBean[] phones;
	private int membersCount, phonesCount, customNum;
	
	public SktelecomServiceImpl() {
		members = new MemberBean[10];
		phones = new PhoneBean[10];
		membersCount = phonesCount = 0;
		customNum = 1000;
	}
	
	@Override
	public String createPhoneNumber() {
		String number = "010-";
		int temp = 0;
		for(int i = 0; i < 2; i++) {
			if(i == 0) {
				temp = (int)(Math.random() * 9000) + 1000;
				number += temp;
				number += "-";
			} else {				
				temp = (int)(Math.random() * 10000);
				number += temp;
			}
		}
		return number;
	}

	@Override
	public String showMessage(MemberBean member, PhoneBean phone) {
		phone.setPhoneNumber(createPhoneNumber());
		addUser(member,phone);
		String message = String.format("[%s]님 이름으로 \n"
				+ "[%s] 으로 [%s]폰이 \n"
				+ "개통되었습니다.", member.getName(),
								phone.getPhoneNumber(),
								phone.getName());
		return message;
	}

	@Override
	public void addUser(MemberBean member, PhoneBean phone) {
		String customNum = createCustomNum();
		member.setCustomNum(customNum);
		phone.setCustomNum(customNum);
		members[membersCount++] = member;
		phones[phonesCount++] = phone;
	}

	@Override
	public String createCustomNum() {
		return String.valueOf(customNum++);
	}

	@Override
	public String[] memberList() {
		String[] res = null;
		int count = 0;
		for(int i = 0 ; 
				i < membersCount || i < phonesCount; 
				i++) {
			if(members[i].getCustomNum().equals(phones[i].getCustomNum())){
				count++;
			}
		}
		if(count == 0) {
			res = new String[1];
			res[0] = "사용자가 없습니다.";
		} else {
			res = new String[count];
			for(int i = 0 ; 
					i < membersCount && i < phonesCount; 
					i++) {
				if(members[i].getCustomNum().equalsIgnoreCase(phones[i].getCustomNum())){
					res[i] = members[i].getName().concat(phones[i].getPhoneNumber());
				}
			}
		}
		return res;
	}
	
}
