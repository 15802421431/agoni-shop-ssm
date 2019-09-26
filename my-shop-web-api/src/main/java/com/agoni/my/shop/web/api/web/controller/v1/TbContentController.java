package com.agoni.my.shop.web.api.web.controller.v1;

import com.agoni.my.shop.commons.dto.BaseResult;
import com.agoni.my.shop.domain.TbContent;
import com.agoni.my.shop.web.api.service.TbContentService;
import com.agoni.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Title TbContentController
 * @Description:
 * @Author Soulmate
 * @Version 1.0
 * @Date 2019/8/28 8:01
 */

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        if (id == null) {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 根据类别 ID 返回内容列表
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "{category_id}",method = RequestMethod.GET)
    public BaseResult ppt(@PathVariable(value = "category_id")Long categoryId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if (tbContents != null && tbContents.size() > 0) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent,dto);
                tbContentDTOS.add(dto);
            }
        }
        return BaseResult.success("成功",tbContentDTOS);
    }

}
