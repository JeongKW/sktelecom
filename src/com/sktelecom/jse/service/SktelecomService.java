package com.sktelecom.jse.service;

import com.sktelecom.jse.domain.MemberBean;
import com.sktelecom.jse.domain.PhoneBean;

public interface SktelecomService {
	public String createPhoneNumber();
	public String showMessage(MemberBean member, PhoneBean phone);
	public void addUser(MemberBean member, PhoneBean phone);
	public String createCustomNum();
	public String[] memberList();
}
