package com.cy.oauth2.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.oauth2.web.entities.SysPermission;
import com.cy.oauth2.web.entities.SysUser;
import com.cy.oauth2.web.mapper.SysPermissionMapper;
import com.cy.oauth2.web.mapper.SysUserMapper;
import com.cy.oauth2.web.service.SysPermissionService;
import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

  @Autowired
  private SysUserMapper sysUserMapper;


    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> list = baseMapper.findByUserId(userId);
        //用户无任何权限时，list会有一个 `null` 空的SysPermission 对象，把那个null移除
        list.remove(null);
        return list;
    }


  @Override
  public Object getNavMeny(Principal principal) {
    SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", principal.getName()));
    List<SysPermission> byUserIds = baseMapper.findByUserIds(sysUser.getId());
    List<SysPermission> menuTreeList = getMenuTreeList(byUserIds, "0");

    return MengxueguResult.ok(sysUser.getNickName(), menuTreeList);
  }


  /**
   * 获取所有菜单树
   * @param menuList
   * @param parentID
   * @return
   */
  private List<SysPermission> getMenuTreeList(List<SysPermission> menuList, String parentID) {
    // 查找所有菜单
    List<SysPermission> childrenList = new ArrayList<>();
    menuList.stream()
      .filter(d -> String.valueOf(d.getParentId()).equals(parentID)).sorted(Comparator.comparing(SysPermission::getSort))
      .collect(Collectors.toList())
      .forEach(d -> {
        d.setChildren(getMenuTreeList(menuList,String.valueOf(d.getId())));
        childrenList.add(d);
      });
    return childrenList;
  }

}
