package com.yingluo.common.api.dubbo;

import com.yingluo.common.api.dto.DictSimpleDTO;

import java.util.List;

/**
 * 字典通用服务接口
 *
 * @author heng
 * @since 2021/12/6
 */
public interface DictionaryService {

    /**
     * 获取业务字典列表（返回值只包含字典code以及value）
     *
     * @param dictCode   字典code，例如：字典"sex:1-男"中，字典code对应"sex"
     * @param systemCode 所属系统
     * @return 字典列表
     */
    List<DictSimpleDTO> getSimpleDictByCode(String dictCode, String systemCode);

    /**
     * 获取单个业务字典列表（返回值只包含字典code以及value）
     *
     * @param dictCode   字典code，例如：字典"sex:1-男"中，字典code对应"sex"
     * @param dictKey    字典类型key，例如：字典"1-男"中，字典key对应"1"
     * @param systemCode 所属系统
     * @return 字典列表
     */
    DictSimpleDTO getSimpleDictByDictCodeAndDictKey(String dictCode, String dictKey, String systemCode);

    /**
     * 获取单个业务字典（返回值只包含字典code以及value）
     *
     * @param dictCode   字典code，例如：字典"sex:1-男"中，字典code对应"sex"
     * @param dictValue  字典值，例如：字典"1-男"中，字典值对应"男"
     * @param systemCode 所属系统
     * @return 字典列表
     */
    DictSimpleDTO getSimpleDictByDictCodeAndDictValue(String dictCode, String dictValue, String systemCode);

}
