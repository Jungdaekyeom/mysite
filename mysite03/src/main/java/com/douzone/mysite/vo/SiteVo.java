package com.douzone.mysite.vo;

public class SiteVo {
	private Long no;
	private String title;
	private String welcome;
	private String profile;
	private String description;

	/**
	 * @return the no
	 */
	public Long getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(Long no) {
		this.no = no;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the welcome
	 */
	public String getWelcome() {
		return welcome;
	}

	/**
	 * @param welcome the welcome to set
	 */
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcome=" + welcome + ", profile=" + profile
				+ ", description=" + description + "]";
	}

}
