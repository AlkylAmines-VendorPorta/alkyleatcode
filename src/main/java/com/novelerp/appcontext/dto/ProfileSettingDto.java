package com.novelerp.appcontext.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;

public class ProfileSettingDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long profileSettingId;
	private AttachmentDto profileLogo;
	private String themeColor;
	private String urlPattern;
	
	public Long getProfileSettingId() {
		return profileSettingId;
	}
	public void setProfileSettingId(Long profileSettingId) {
		this.profileSettingId = profileSettingId;
	}
	public AttachmentDto getProfileLogo() {
		return profileLogo;
	}
	public void setProfileLogo(AttachmentDto profileLogo) {
		this.profileLogo = profileLogo;
	}
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	

}
