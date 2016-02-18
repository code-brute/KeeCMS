/*
 *	Copyright © 2013 Changsha kee Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.kee.com
 */

package com.kee.cms.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kee.cms.entity.vo.ArticleVo;
import com.kee.cms.entity.vo.PageVo;
import com.kee.cms.exception.FolderNotFoundException;
import com.kee.cms.service.ArticleService;
import com.kee.cms.service.FolderService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Herbert
 * 
 */
@Service
public class ArticlePageTag implements TemplateDirectiveModel {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer folderId = Integer.parseInt(params.get("folderId").toString());
		Integer p = Integer.parseInt(params.get("p").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());
		// 获取文件的分页
		try {
			PageVo<ArticleVo> pageVo = articleService.getArticlePageByFolderId(
					folderId, p, rows);
			env.setVariable("tag_article_page", BEANS_WRAPPER.wrap(pageVo));
		} catch (FolderNotFoundException e) {
			env.setVariable("tag_article_page", BEANS_WRAPPER.wrap(null));
		}

		body.render(env.getOut());
	}

}
