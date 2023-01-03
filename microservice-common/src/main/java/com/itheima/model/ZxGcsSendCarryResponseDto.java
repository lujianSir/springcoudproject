package com.itheima.model;

import lombok.Data;

/**
 * @author: wuxl
 * @create: 2021-08-30 16:12
 * @Version: V1.0
 */
@Data
public class ZxGcsSendCarryResponseDto {

    @ApiModelProperty(value = "32位uuid")
    private String id;

    @ApiModelProperty(value = "到位回告时间")
    private String billDate;

    @ApiModelProperty(value = "EIS生成的任务单号")
    private String billCode;

    @ApiModelProperty(value = "执行母托盘编号")
    private String containerCode;

    @ApiModelProperty(value = "系统类型：1:GCS  2:MCS")
    private int sysType;

    @ApiModelProperty(value = "目的位置")
    private String locCodeTo;

    @ApiModelProperty(value = "当前位置")
    private String position;

    @ApiModelProperty(value = "作业状态:1:开始  2:结束")
    private int workStatus;

    @ApiModelProperty(value = "小车编号")
    private String rgvId;

    @ApiModelProperty(value = "任务类型：1:入库 2:出库 3:搬运 4:移动到巷道口 5:移动到储位")
    private int taskType;
}
