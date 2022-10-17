package cn.mybook.service.impl;

import cn.mybook.entity.Admin;
import cn.mybook.entity.RespBean;
import cn.mybook.mapper.AdminMapper;
import cn.mybook.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public RespBean adminLogin(Admin admin, HttpServletRequest request) {
        Admin getAdmin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", admin.getUsername()));
        if(getAdmin!=null && getAdmin.getPassword().equals(admin.getPassword())){
            return RespBean.success("登录成功",getAdmin);
        }else {
            return RespBean.error("账号或密码错误,请重新输入");
        }
    }
}
