package com.itheima.model;

import lombok.Data;

/**
 * @author: wuxl
 * @create: 2021-08-27 16:44
 * @Version: V1.0
 */
@Data
public class ZxGcsCarryResponseDto {

    @ApiModelProperty("返回值")
    private boolean ret;

    @ApiModelProperty("信息")
    private String msg;

    public ZxGcsCarryResponseDto(boolean ret,String msg){
        this.ret=ret;
        this.msg=msg;
    }
}
