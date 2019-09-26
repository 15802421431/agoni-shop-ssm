/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ContentsApi
 * Author:   Soulmate
 * Date:     2019/9/1 18:30
 * Description: 内容管理接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.web.ui.api;

import com.agoni.my.shop.commons.utils.HttpClientUtils;
import com.agoni.my.shop.commons.utils.MapperUtils;
import com.agoni.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈内容管理接口〉
 *
 * @author Soulmate
 * @create 2019/9/1
 * @since 1.0.0
 */
public class ContentsApi {
    public static List<TbContent> ppt(String id) {
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS+id);
        try {
            tbContents = MapperUtils.json2listByTree(result,"data",TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContents;
    }
}
