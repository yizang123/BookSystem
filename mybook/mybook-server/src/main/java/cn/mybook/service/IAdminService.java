package cn.mybook.service;

import cn.mybook.entity.Admin;
import cn.mybook.entity.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
public interface IAdminService extends IService<Admin> {

    RespBean adminLogin(Admin admin, HttpServletRequest request);
}
