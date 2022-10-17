package cn.mybook.api.controller;


import cn.mybook.entity.Admin;
import cn.mybook.entity.RespBean;
import cn.mybook.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@RestController
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RespBean adminLogin(@RequestBody Admin admin, HttpServletRequest request){
        return adminService.adminLogin(admin,request);
    }


}
