package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Log LOGGER = LogFactory.getLog(AdminController.class);

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private SiteService siteService;

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String main(Model model) {
		model.addAttribute("site", siteService.getSite());
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String update(SiteVo site, @RequestParam("file") MultipartFile file) {
		try {
			String profile = galleryService.saveAdminImage(file);
			site.setProfile(profile);
		} catch (FileUploadException ex) {
			LOGGER.info("Admin Main Update:" + ex);
		}

		siteService.update(site);
		servletContext.setAttribute("site", site);

		return "redirect:/admin";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

}