package com.example.backend.controller;

import com.example.backend.entity.Contact;
import com.example.backend.entity.domain.ContactType;
import com.example.backend.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
	private final ContactService contactService;

	@GetMapping
	public ResponseEntity<List<Contact>> getAll() {
		return ResponseEntity.ok(contactService.findAll());
	}

	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getById(@PathVariable Integer contactId) {
		return ResponseEntity.ok(contactService.findById(contactId));
	}

	@PostMapping
	public ResponseEntity<Contact> save(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.save(contact));
	}

	@PutMapping
	public ResponseEntity<Contact> update(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.update(contact));
	}

	@PutMapping("/{contactId}")
	public ResponseEntity<Contact> updateById(@RequestBody Contact contact, @PathVariable Integer contactId) {
		contact.setId(contactId);
		return ResponseEntity.ok(contactService.update(contact));
	}

	@DeleteMapping("/{contactId}")
	public void deleteById(@PathVariable Integer contactId) {
		contactService.deleteById(contactId);
	}

	@GetMapping("/contactTypes")
	public ResponseEntity<Object[]> getContactType() {
		return ResponseEntity.ok(ContactType.values());
	}

}

