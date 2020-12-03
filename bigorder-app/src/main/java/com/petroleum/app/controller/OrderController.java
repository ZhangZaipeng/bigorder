package com.petroleum.app.controller;

import com.petroleum.app.common.response.ResponseModel;
import com.petroleum.app.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/28   10:13
 */
@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/produceData.json")
  @ApiOperation(value = "生成接口", httpMethod = "POST")
  public ResponseModel produceData() {
    return ResponseModel.ok(orderService.produceData());
  }

  @GetMapping(value = "/selectOrderById.json")
  @ApiOperation(value = "selectOrderById", httpMethod = "GET")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "orderId",
          required = true, paramType = "query", dataType = "string"),
  })
  public ResponseModel selectOrderById(@RequestParam("orderId") String orderId) {
    return ResponseModel.ok(orderService.selectOrderById(orderId));
  }


}
