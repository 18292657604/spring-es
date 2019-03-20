package com.skycloud.mapper;

import com.skycloud.entity.Xici;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by angel on 2019/1/4.
 */
@Mapper
public interface XiciMapper {

    List<Xici> getXiciList(String ip);

}
