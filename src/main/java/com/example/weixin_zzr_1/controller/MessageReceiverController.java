package com.example.weixin_zzr_1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 控制器 : 负责接收用户的请求参数、调用业务逻辑层代码、返回视图/结果给客户端（浏览器）
// @Controller  基于JSP的控制器
// @RestController 符合RESTful风格的WEB服务的控制器
// RESTful通过不同的请求方法调用不同的处理程序，返回的结果仅仅是数据，不包含视图（HTML、JSP）
@RestController
// 各自写代码的时候，把/zzr_1改为【/拼音名】，用于后面作为路径反向代理的时候区分不同人的代码
// @RequestMapping表示的含义：URL跟控制器的关系映射
@RequestMapping("/zzr_1/weixin/receiver")
public class MessageReceiverController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverController.class);

	@GetMapping // 只处理GET请求
	public String echo(//
			@RequestParam("signature") String signature, //
			@RequestParam("timestamp") String timestamp, //
			@RequestParam("nonce") String nonce, //
			@RequestParam("echostr") String echostr//
	) {
		// 正常来讲，需要把timestamp和nonce放入一个数组，并进行排序
		// 接着把排序后的两个元素拼接成一个新的String
		// 使用SHA-1算法对新的String进行加密
		// 最后把加密的结果跟signature进行比较，如果相同表示验证通过，返回echostr
		System.out.println("打印测试");
		// 原路返回echostr的值，返回以后微信公众号平台就能够认为：服务器对接成功
		return echostr;
	}
	
		// 当微信客户端发送任意消息给公众号的时候，消息都会通过POST方式提交到当前类里面。
		// @PostMapping专门用于处理POST请求。
		// 消息的格式是XML形式的字符串，整个消息放入了请求体里面。
		@PostMapping
		public String onMessage(@RequestParam("signature") String signature, //
				@RequestParam("timestamp") String timestamp, //
				@RequestParam("nonce") String nonce, //
				@RequestBody String xml) {
			LOG.debug("收到用户发送给公众号的信息: \n-----------------------------------------\n"
					+ "{}\n-----------------------------------------\n", xml);
			// 由于后面会把消息放入队列中，所以这里直接返回success。
			return "success";
		}
}
