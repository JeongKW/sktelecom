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
				number += temp + "-";
			} else {
				temp = (int)(Math.random() * 10000);
				number += (temp < 1000) ? "0" + temp : temp;
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
				i < membersCount && i < phonesCount; 
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
					res[i] = "[고객명] " + members[i].getName().concat(" [번호] " + phones[i].getPhoneNumber());
				}
			}
		}
		return res;
	}

	@Override
	public String findByKey(String key) {
		String res = "";
		for(int i = 0; i < membersCount; i++) {
			if(key.equalsIgnoreCase(members[i].getCustomNum()) 
				&& key.equalsIgnoreCase(phones[i].getCustomNum())) {
				res = String.format("[고객번호] %s [고객명] %s [번호] %s [기종] %s\n",
						members[i].getCustomNum(), members[i].getName(), 
						phones[i].getPhoneNumber(), phones[i].getName());
				break;
			}
		}
		return res;
	}

	@Override
	public String findByName(String name) {
		String res = "";
		for(int i = 0; i < membersCount; i++) {
			if(name.equalsIgnoreCase(members[i].getName())) {
				res += String.format("[고객번호] %s [고객명] %s [번호] %s [기종] %s\n",
						members[i].getCustomNum(), members[i].getName(), 
						phones[i].getPhoneNumber(), phones[i].getName());
			}
		}
		return res;
	}

	@Override
	public void updatePhoneNumber(String key) {
		for(int i = 0; i < membersCount; i++) {
			if(key.equalsIgnoreCase(members[i].getCustomNum())) {
				phones[i].setPhoneNumber(createPhoneNumber());
				break;
			}
		}
	}

	@Override
	public void deleteMember(String key) {
		PhoneBean[] tempPhones = phones;
		MemberBean[] tempMembers = members;
		for(int i = 0; i < membersCount; i++) {
			if(key.equalsIgnoreCase(tempMembers[i].getCustomNum())) {
				members = new MemberBean[membersCount-1];
				phones = new PhoneBean[phonesCount-1];
				for(int j = i; j < membersCount-1; j++) {
					tempPhones[j] = tempPhones[j+1];
					tempMembers[j] = tempMembers[j+1];
				}
				break;
			}
		}
		for(int i = 0; i < membersCount-1; i++) {
			phones[i] = tempPhones[i];
			members[i] = tempMembers[i];
		}
		membersCount--;
		phonesCount--;
	}
}
