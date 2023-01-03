package com.itheima.model;

import lombok.Data;

/**
 * @author: wuxl
 * @create: 2021-08-27 16:44
 * @Version: V1.0
 */
@Data
public class ZxMcsCarryRequestDto {

    @ApiModelProperty("任务ID")
    private String taskId;

    @ApiModelProperty("容器编号")
    private String stockId;

    @ApiModelProperty("库编号")
    private int bankId;

    @ApiModelProperty("任务类型：1输送线入库 2输送线出库 3提升机入库 4提升机出库")
    private int type;

    @ApiModelProperty("优先级：越小越高")
    private int priority;

    @ApiModelProperty("源位置")
    private String source;

    @ApiModelProperty("目标位置")
    private String target;

    @ApiModelProperty("重量")
    private String weight;

    @ApiModelProperty("任务状态")
    private int status;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("mcs/gcs 下传字段")
    private String expand1;

}
