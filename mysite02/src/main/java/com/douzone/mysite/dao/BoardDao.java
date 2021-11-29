
package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

public class BoardDao {
	public List<BoardVo> findAllByTen(Long startPost, Long endPost) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 게시판 SQL문
			// 이름을 얻기 위해 조인함.
			// no는 헷갈리지 말자. 게시판의 번호를 우선으로 둬야 한다.
			String sql = "select b.no, a.name, title, contents, hit, "
					+ "date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, "
					+ "group_no, order_no, depth, user_no, delete_bool"
					+ " from user a join board b on a.no = b.user_no order by group_no desc, order_no asc limit ?, ?";

			pstmt = conn.prepareStatement(sql);

			// 익스큐트 하기 전에 바인딩(물음표 두개)
			// 페이지를 받아와서 pstmt로 바인딩
			pstmt.setLong(1, startPost);
			pstmt.setLong(2, endPost);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 게시판 번호
				Long no = rs.getLong(1);
				// '조인'한 유저의 이름
				String name = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Long hit = rs.getLong(5);
				String regDate = rs.getString(6);
				Long groupNo = rs.getLong(7);
				Long orderNo = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long userNo = rs.getLong(10);
				Long deleteBool = rs.getLong(11);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setUserName(name);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setDeleteBool(deleteBool);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<BoardVo> findSearchAllByTen(Long startPost, Long endPost, String searchType, String kwd) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 게시판 SQL문
			// 이름을 얻기 위해 조인함.
			// no는 헷갈리지 말자. 게시판의 번호를 우선으로 둬야 한다.
			String sql = "select b.no, a.name, b.title, b.contents, b.hit, "
					+ " date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, "
					+ " b.group_no, b.order_no, b.depth, b.user_no, b.delete_bool "
					+ " from user a join board b on a.no = b.user_no where " + searchType
					+ " like ? order by b.group_no desc, b.order_no asc limit ?, ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + kwd + "%");
			pstmt.setLong(2, startPost);
			pstmt.setLong(3, endPost);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 게시판 번호
				Long no = rs.getLong(1);
				// '조인'한 유저의 이름
				String name = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Long hit = rs.getLong(5);
				String regDate = rs.getString(6);
				Long groupNo = rs.getLong(7);
				Long orderNo = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long userNo = rs.getLong(10);
				Long deleteBool = rs.getLong(11);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setUserName(name);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setDeleteBool(deleteBool);
				System.out.println("vo = " + vo);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 게시판 SQL문
			// 이름을 얻기 위해 조인함.
			// no는 헷갈리지 말자. 게시판의 번호를 우선으로 둬야 한다.
			String sql = "select no from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 게시판 번호
				Long no = rs.getLong(1);
				BoardVo vo = new BoardVo();

				vo.setNo(no);

				list.add(vo);
				////////////////////////

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<BoardVo> findSearchAll(String searchType, String kwd) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 게시판 SQL문
			// 이름을 얻기 위해 조인함.
			// no는 헷갈리지 말자. 게시판의 번호를 우선으로 둬야 한다.
			String sql = "select b.no from board a join user b on a.user_no = b.no where a.delete_bool = 1 and " + searchType + " like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kwd + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 게시판 번호
				Long no = rs.getLong(1);
				BoardVo vo = new BoardVo();

				vo.setNo(no);

				list.add(vo);
				////////////////////////

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public BoardVo findByNo(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, title, contents, hit, reg_date, group_no, order_no, depth, user_no, delete_bool from board where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVo();

				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOrderNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));
				vo.setDeleteBool(rs.getLong(10));

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public Long findByMaxGroupNo() {
		Long maxGroupNo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select max(group_no) from board";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxGroupNo = rs.getLong(1);
				return maxGroupNo;
			} else {
				System.out.println("sql문 실행됐음");
			}
			System.out.println(maxGroupNo);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return maxGroupNo;
	}

	public BoardVo hitCountUp(Long no, Long hit) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "update board set hit=? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, hit + 1);
			pstmt.setLong(2, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVo();

				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOrderNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));
				vo.setDeleteBool(rs.getLong(10));

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public BoardVo modify(String title, String content, Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "update board set title=?, contents=?, reg_date=now() where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVo();

				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOrderNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public BoardVo delete(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "update board set delete_bool=0 where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVo();

				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOrderNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	// 메인화면의 글쓰기 함수
	public boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {

			// 답글이 아니고 메인 글쓰기이므로, 깊이는 무조건 0
			// 글쓰기와 마찬가지로 글을 작성한 시점에서 조회수가 카운팅될 수 없으므로 hits도 기본값은 0

			// IFNULL(expression1, expression2) 이용을 고려해볼 것
			// expression1 이 NULL이 아니면 expression1을, NULL이면 expression2를 반환합니다.

			// if : 글쓰기에서 넘어왔으므로 깊이값은 0
			if (vo.getDepth() == 0) {
				conn = getConnection();
				String sql = "insert into board values (null, ?, ?, 0, now(), ?, 0, 0, ?, 1)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				// 메인 글쓰기일 경우 그룹 넘버를 1 올리고
				// 오더 넘버와 뎁스 넘버를 0으로 초기화
				pstmt.setLong(3, vo.getGroupNo() + 1);
				pstmt.setLong(4, vo.getUserNo());
			}
			// 답글에서 넘어왔으므로 깊이값은 1 이상
			else {
				conn = getConnection();
				System.out.println(vo);

				String sql2 = "update board set order_no = (order_no + 1) where group_no = ? and order_no >= ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setLong(1, vo.getGroupNo());
				pstmt2.setLong(2, vo.getOrderNo());
				pstmt2.executeUpdate();

				String sql = "insert into board values (null, ?, ?, 0, now(), ?, ?, ?, ?, 1)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				// 답글일 경우 그룹 넘버를 유지
				pstmt.setLong(3, vo.getGroupNo());
				pstmt.setLong(4, vo.getOrderNo());
				pstmt.setLong(5, vo.getDepth());
				pstmt.setLong(6, vo.getUserNo());

			}

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}