package cn.mybook.api.controller;


import cn.mybook.entity.Reader;
import cn.mybook.entity.RespBean;
import cn.mybook.entity.RespPageBean;
import cn.mybook.service.IReaderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private IReaderService readerService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RespPageBean getReaderList(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size){
        return readerService.getReaderList(currentPage,size);
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Reader> getReaderByMap(@RequestParam(value = "phone",required = false)String phone,@RequestParam(value = "name",required = false)String name){
        Map<String,Object> map = new HashMap<>();
           map.put("phone",phone);
           map.put("name",name);

        return readerService.getReaderByMap(map);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RespBean updateReaderInfo(@RequestBody Reader reader){
        boolean update = readerService.updateById(reader);
        if(update){
            return RespBean.success("修改读者信息成功");
        }
        return RespBean.error("修改读者信息失败");
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public RespBean deleteReaderById(@RequestParam(value="id") int id){
        return readerService.deleteReaderById(id);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBean addReader(@RequestBody Reader reader){
        Reader getOne = readerService.getOne(new QueryWrapper<Reader>().eq("phone", reader.getPhone()));
        if(getOne==null){
            boolean save = readerService.save(reader);
            if(save){
                return RespBean.success("添加成功");
            }
        }else{
            return RespBean.error("手机号已绑定");
        }
        return RespBean.error("数据库异常信息!");
    }
    @RequestMapping(value = "/deleteBatch/id",method = RequestMethod.DELETE)
    public RespBean deleteBatch(@RequestParam("id") List<Integer> id){
        RespBean respBean = null;
        for (Integer integer : id) {
            respBean = readerService.deleteReaderById(integer);
        }
        return respBean;
    }


}
