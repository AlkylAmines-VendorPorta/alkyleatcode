package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;

@Entity
@Table(name = "m_profile_setting")
public class ProfileSetting extends ContextPO{
	
	
	private static final long serialVersionUID = 1L;
	private Long profileSettingId;
	private Attachment profileLogo;
	private String themeColor;
	private String urlPattern;
	
	@Id
	@SequenceGenerator(name = "m_profile_setting_seq", sequenceName = "m_profile_setting_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_profile_setting_seq")
	@Column(name = "m_profile_setting_id", updatable = false)
	public Long getProfileSettingId() {
		return profileSettingId;
	}
	public void setProfileSettingId(Long profileSettingId) {
		this.profileSettingId = profileSettingId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_logo")
	public Attachment getProfileLogo() {
		return profileLogo;
	}
	public void setProfileLogo(Attachment profileLogo) {
		this.profileLogo = profileLogo;
	}
	@Column(name="theme_color")
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	@Column(name="url_pattern")
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	

}
