package com.agoni.my.shop.web.admin.web.controller;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.domain.TbContentCategory;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.agoni.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 *
 * @Title ContentCategoryController
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/6/12 15:59
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {


    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        // id 不为空，则从数据库获取
        if (id != null) {
            tbContentCategory = service.getById(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }

        return tbContentCategory;
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        //目标数据(元数据排序后的数据)
        List<TbContentCategory> targetList = new ArrayList<>();
        //元数据(从数据库中直接调出来的)
        List<TbContentCategory> sourceList = service.selectAll();
        //调用排序
        sortList(sourceList, targetList, 0L);
        //往前端传，当然是排序后的目标数据
        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }


    /**
     * 树形结构
     *
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        return service.selectByPid(id);
    }

    /**
     * 跳转表单
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }

    /**
     * 保存
     *
     * @param tbContentCategory
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbContentCategory);
        }
    }

}
