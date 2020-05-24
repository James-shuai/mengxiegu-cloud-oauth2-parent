package com.mengxuegu.oauth2.web.controller;

import com.mengxuegu.base.result.ResultData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ys
 * @date 2020/4/23 9:47
 */
@RestController
@RequestMapping("/product")
public class ProductController {

  @RequestMapping("/list")
  @PreAuthorize("hasAnyAuthority('sys:manage')")
  public ResultData list(){
    List<String> list= new ArrayList<>();
    list.add("眼镜");
    list.add("衬衫");
    list.add("牛仔裤");
    return ResultData.ok(list);
  }


}
