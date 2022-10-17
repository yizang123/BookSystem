package cn.mybook.service;

import cn.mybook.entity.Reader;
import cn.mybook.entity.RespBean;
import cn.mybook.entity.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
public interface IReaderService extends IService<Reader> {



    RespPageBean getReaderList(Integer currentPage, Integer size);

    List<Reader> getReaderByMap(Map<String, Object> map);

    RespBean deleteReaderById(int id);
    int getRid(String phone);


}
