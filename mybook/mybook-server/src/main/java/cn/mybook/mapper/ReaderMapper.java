package cn.mybook.mapper;

import cn.mybook.entity.Reader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@Repository
public interface ReaderMapper extends BaseMapper<Reader> {
    IPage<Reader> getReaderList(@Param("page") Page<Reader> page);
    List<Reader> getReaderByMap(Map<String,Object> map);
    int getDistinctValue();
    List<Reader> getBidList(Map<String,Object> map);
    int getRid(String phone);
}
