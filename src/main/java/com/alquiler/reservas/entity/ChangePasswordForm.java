package com.alquiler.reservas.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ChangePasswordForm {
	
	@NotNull Long id;
	@NotBlank(message="Current Password must not be blank")
	private String currentPassword;
	
	@NotBlank(message="New Password must not be blank")
	private String newPassword;
	
	@NotBlank(message="Confirm Password must not be blank")
	private String confirmPassword;
	
	public ChangePasswordForm() { }
	public ChangePasswordForm(Long id) {this.id = id;}
	
	//Getters & Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
	}
