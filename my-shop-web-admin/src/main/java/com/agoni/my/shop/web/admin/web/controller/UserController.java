package com.agoni.my.shop.web.admin.web.controller;

import com.agoni.my.shop.commons.constant.ConstantUtils;
import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.commons.dto.PageInfo;
import com.agoni.my.shop.domain.TbUser;
import com.agoni.my.shop.web.admin.abstracts.AbstractBaseController;
import com.agoni.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {


    @ModelAttribute
    public TbUser getById(Long id){
        TbUser tbUser = null;
        //没有id，就new一个
        if (id == null || id == 0){
            tbUser = new TbUser();
        }
        //有id，就从数据库中获取
        else{
            tbUser = service.getById(id);
        }
        return tbUser;
    }

    /**
     * 跳转到用户列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 保存用户信息
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model,RedirectAttributes redirectAttributes){

        BaseResult baseResult = service.save(tbUser);

        //保存成功 重定向到用户列表界面
        if (baseResult.getStatus() == ConstantUtils.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败 跳转到表单界面，展示错误信息
        else{
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 删除用户信息(json格式返回)
     * @param ids
     * @return
     */
    @Override
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete (String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNoneBlank(ids)){
            String[] array = ids.split(",");
            service.deleteMulti(array);
            baseResult = BaseResult.success("删除用户成功!");
        }

        else{
            baseResult = BaseResult.fail("删除用户失败!");
        }
        return baseResult;
    }



    /**
     * 显示用户详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    //因为会在@Requestmapping之前执行@ModelAttribute,直接会new一个tbUser.
    public String detail(){
        return "user_detail";
    }
}
