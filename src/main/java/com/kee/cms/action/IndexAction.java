package com.kee.cms.action;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kee.cms.exception.TemplateNotFoundException;
/**
 * 首页/404/500处理页
 * @author keehang
 *
 */
@Controller
public class IndexAction extends BaseAction {

	/**
	 * 首页
	 * 
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String home(@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			modelMap.addAttribute("p", p);
			return themeService.getDefaultTemplate();
		} catch (TemplateNotFoundException e) {
			logger.fatal(e.getMessage());
			return themeService.getTemplatePath("404");
		}
	}

	/**
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping(value = "/404.htm", method = RequestMethod.GET)
	public String pageNotFound() {
		return themeService.getTemplatePath("404");
	}

	/**
	 * 500
	 * 
	 * @return
	 */
	@RequestMapping(value = "/500.htm", method = RequestMethod.GET)
	public String error() {
		return themeService.getTemplatePath("500");
	}
}
