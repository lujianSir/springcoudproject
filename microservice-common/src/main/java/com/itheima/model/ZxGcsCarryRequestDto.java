package com.itheima.model;

import lombok.Data;

/**
 * @author: wuxl
 * @create: 2021-08-27 16:44
 * @Version: V1.0
 */
@Data
public class ZxGcsCarryRequestDto {

    @ApiModelProperty("任务ID")
    private String taskId;

    @ApiModelProperty("容器编号")
    private String containerId;

    @ApiModelProperty("层")
    private int layer;

    @ApiModelProperty("层")
    private int floor;

    @ApiModelProperty("任务类型：1入库 2出库 3移库")
    private int taskType;

    @ApiModelProperty("优先级：越小越高")
    private int priority;

    @ApiModelProperty("源位置")
    private String locIdFrom;

    @ApiModelProperty("目标位置")
    private String locIdTo;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("设备厂商")
    private String equipmentFirm;

    @ApiModelProperty("小车编号")
    private String rgvId;

    @ApiModelProperty("mcs/gcs 下传字段")
    private String expand1;
}
