package com.atcollabo.service;

import java.util.List;

import com.atcollabo.entity.ContactInquery;

public interface ContactInqueryService {

	Object addContactInquery(ContactInquery contact);

	List<ContactInquery> getListContactInquery();

}
