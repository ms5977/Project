package com.blog.mains.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.blog.mains.services.RoleService;

@Component
public class ApplicationStartupListener {
	@Autowired
	private RoleService roleService;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationEvent() {
		roleService.savedRoles();
	}
}
