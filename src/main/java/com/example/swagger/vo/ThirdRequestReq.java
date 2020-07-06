package com.example.swagger.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("ThirdRequestReqsss")
public class ThirdRequestReq implements Serializable {
    private static final long serialVersionUID = -8338312410163859789L;

    @ApiModelProperty(example = "12345",name = "订单编号",value = "这里添加订单编号")
    private String orderNumber;
    @ApiModelProperty(example = "11",name = "商品数量",value = "只能写整数")
    private Integer num;
}
