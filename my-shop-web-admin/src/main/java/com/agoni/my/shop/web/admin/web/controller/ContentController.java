package com.agoni.my.shop.web.admin.web.controller;

import com.agoni.my.shop.commons.constant.ConstantUtils;
import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.domain.TbContent;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseController;
import com.agoni.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Title ContentController
 * @Description:内容管理
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/6/16 7:50
 */

@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService> {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getById(Long id){
        TbContent tbContent = null;
        //有id，就从数据库中获取
        if (id != null){
            tbContent = tbContentService.getById(id);
        }
        //没有id，就new一个
        else{
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到内容列表页
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 跳转内容表单页
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     * 保存信息
     * @param tbContent
     * @param model 跳转(转发)参数
     * @param redirectAttributes 重定向参数
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){

        BaseResult baseResult = tbContentService.save(tbContent);

        //保存成功 重定向到用户列表界面
        if (baseResult.getStatus() == ConstantUtils.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //保存失败 跳转到表单界面，展示错误信息
        else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    /**
     * 删除信息(json格式返回)
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete (String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNoneBlank(ids)){
            String[] array = ids.split(",");
            tbContentService.deleteMulti(array);
            baseResult = BaseResult.success("删除内容成功!");
        }

        else{
            baseResult = BaseResult.fail("删除内容失败!");
        }
        return baseResult;
    }

    /**
     * 显示详情
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    //因为会在@Requestmapping之前执行@ModelAttribute,直接会new一个tbContent.
    public String detail(){
        return "content_detail";
    }
}
