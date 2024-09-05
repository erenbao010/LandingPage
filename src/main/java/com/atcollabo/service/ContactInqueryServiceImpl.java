package com.atcollabo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcollabo.entity.ContactInquery;
import com.atcollabo.repository.ContactInqueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ContactInqueryServiceImpl implements ContactInqueryService {

	private final ContactInqueryRepository contactRepository;

	@Override
	public Object addContactInquery(ContactInquery contact) {
		return contactRepository.save(contact);
	}

	@Override
	public List<ContactInquery> getListContactInquery() {
		return contactRepository.findAllByActiveOrderByInqPkDesc(true);
	}

}
