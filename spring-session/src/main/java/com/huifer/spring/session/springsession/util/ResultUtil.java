package com.huifer.spring.session.springsession.util;

import com.huifer.spring.session.springsession.vo.ResultVo;

/**
 * <p>Title : ResultUtil </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
public class ResultUtil {

    /**
     * 成功操作返回
     */
    public static ResultVo success(Object o) {
        ResultVo<Object> ok = ResultVo.builder().code(200).msg("ok").data(o).build();
        return ok;
    }

    /**
     * 成功操作返回
     */
    public static ResultVo success() {
        return success(null);
    }

    /**
     * 失败操作返回
     */
    public static ResultVo error(Integer code, String msg) {
        ResultVo<Object> build = ResultVo.builder().code(code).msg(msg).build();
        return build;
    }
}
