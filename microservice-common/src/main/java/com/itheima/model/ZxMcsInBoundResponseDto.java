package com.itheima.model;

import lombok.Data;

/**
 * @author: wuxl
 * @create: 2021-08-27 16:44
 * @Version: V1.0
 */
@Data
public class ZxMcsInBoundResponseDto {

    @ApiModelProperty("任务号")
    private String taskId;

    @ApiModelProperty("任务类型：1输送线入库 2输送线出库 3提升机入库 4提升机出库")
    private int type;

    @ApiModelProperty("母容器编号")
    private String stockId;

    @ApiModelProperty("子容器编号")
    private String stockIdSub;

    @ApiModelProperty("请求位置：源坐标")
    private String source;

    @ApiModelProperty("目的位置：目的坐标")
    private String target;

    @ApiModelProperty("入库重量")
    private String weight;

    @ApiModelProperty("尺寸检测信息：1正常 2左超长 3右超长 4上超长 5下超长 6超高")
    private int detection;

    @ApiModelProperty("任务优先级,0-99,0优先级最大")
    private int priority;

    @ApiModelProperty("任务状态：1任务开始 2任务完成")
    private int status;

    @ApiModelProperty("高度")
    private int height;

}
