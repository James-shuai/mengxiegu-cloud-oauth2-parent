package com.cy.oauth2.web.controller;

import com.cy.oauth2.web.entities.SysPermission;
import com.cy.oauth2.web.service.SysPermissionService;
import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ys
 * @date 2020/4/27 13:45
 */
@RestController
@RequestMapping("/system/menu")
public class SysPermissionController {

  @Autowired
  private SysPermissionService sysPermissionService;


  @RequestMapping("/getNavMenu")
  public Object getNavMenu(Principal principal){

    return sysPermissionService.getNavMeny(principal);
  }



}
