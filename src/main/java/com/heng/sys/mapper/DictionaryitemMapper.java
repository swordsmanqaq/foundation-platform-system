package com.heng.sys.mapper;

import com.heng.sys.domain.Dictionaryitem;
import com.heng.base.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvis-Smith
 * @since 2023-03-13
 */
public interface DictionaryitemMapper extends BaseMapper<Dictionaryitem> {
    List<Dictionaryitem> loadItemById(Long id);
}
