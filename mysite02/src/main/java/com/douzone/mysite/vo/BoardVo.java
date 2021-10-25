package com.douzone.mysite.vo;

public class BoardVo {

	private Long no;
	private String title;
	private String contents;
	private Long hit;
	private String regDate;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	private Long userNo;
	// 유저 이름을 갖고 오기 위한 조인용으로 추가
	private String userName;
	// DB전체 갯수를 갖고옴
	private Long deleteBool;

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
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the hit
	 */
	public Long getHit() {
		return hit;
	}

	/**
	 * @param hit the hit to set
	 */
	public void setHit(Long hit) {
		this.hit = hit;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the groupNo
	 */
	public Long getGroupNo() {
		return groupNo;
	}

	/**
	 * @param groupNo the groupNo to set
	 */
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	/**
	 * @return the orderNo
	 */
	public Long getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the depth
	 */
	public Long getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(Long depth) {
		this.depth = depth;
	}

	/**
	 * @return the userNo
	 */
	public Long getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the deleteBool
	 */
	public Long getDeleteBool() {
		return deleteBool;
	}

	/**
	 * @param deleteBool the deleteBool to set
	 */
	public void setDeleteBool(Long deleteBool) {
		this.deleteBool = deleteBool;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo
				+ ", userName=" + userName + ", deleteBool=" + deleteBool + "]";
	}

}
