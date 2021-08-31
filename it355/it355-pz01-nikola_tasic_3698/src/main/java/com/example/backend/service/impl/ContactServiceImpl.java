package com.example.backend.service.impl;

import com.example.backend.entity.Contact;
import com.example.backend.repository.ContactRepository;
import com.example.backend.service.ContactService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ContactServiceImpl implements ContactService {
	private final ContactRepository contactRepository;

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findById(Integer contactId) {
		return contactRepository.findById(contactId)
				.orElseThrow(() -> new NoSuchElementException("ContactService.notFound"));
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Contact update(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void deleteById(Integer contactId) {
		contactRepository.deleteById(contactId);
	}
}