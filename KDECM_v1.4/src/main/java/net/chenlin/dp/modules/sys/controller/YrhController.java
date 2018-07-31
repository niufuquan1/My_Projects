package net.chenlin.dp.modules.sys.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;
import net.chenlin.dp.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统日志
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月14日 下午10:01:36
 */
@RestController
@RequestMapping("/yrh")
public class YrhController extends AbstractController {

	@RequestMapping("/test")
	public String  getTest()
    {
        return "测试api";
    }

}
