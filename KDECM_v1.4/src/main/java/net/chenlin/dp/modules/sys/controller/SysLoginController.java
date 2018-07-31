package net.chenlin.dp.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.chenlin.dp.common.utils.ImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.common.utils.SendEmail;
import net.chenlin.dp.common.utils.ShiroUtils;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户controller
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月8日 下午2:48:50
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private Producer producer;// =ImageCodeUtils.getInstance().getProducer();

	/**
	 * 验证码
	 * 
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/captcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		// HttpSession session = request.getSession();
		// System.out.println("session = " + session.getId());
		// session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@SysLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password, String captcha, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

		/*if (!captcha.equalsIgnoreCase(kaptcha)) {
			return R.error("验证码不正确");
		}*/
		SysUserEntity user = sysUserService.getByUserName(username);
		if (user == null) {
			return R.error("用户名不存在");
		}
		if (user != null) {
			username = user.getUsername();
		}
		password = MD5Utils.encrypt(username, password);
		if (!user.getPassword().equals(password)) {
			return R.error("用户名或密码错误");
		}

		if (user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}

		R r = new R();
		r = sysUserService.saveUserToken(user.getUserId());
		r.put("username", username);
		r.put("userId", user.getUserId());

		/**
		 * author:zmy
		 */
		// response.setContentType("json/html;charset=utf-8");
	/*	HttpSession session = request.getSession();
		// 自己把SessionID保存在cookie中
		session.setAttribute("userName", username);
		System.out.println(session.getAttribute(("userName").toString()));
		// Cookie cookie = new Cookie("JSESSIONID",session.getId());
		Cookie cookie = new Cookie("username", username);
		System.out.println("---" + cookie.toString() + "----");
		cookie.setMaxAge(60 * 120);
		System.out.println("------" + cookie.getName() + "------" + cookie.getValue() + "------" + cookie.getDomain()
				+ "------" + cookie.getMaxAge() + "------");
		response.addCookie(cookie);*/
		return r;
	}

	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public R logout() {
		R r = sysUserService.updateUserToken(getUserId());
		ShiroUtils.logout();
		return r;
	}

	@SysLog("忘记密码")
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public R forgetpassword(String validateCode, int flag) throws IOException {
		SysUserEntity sysuserentity = null;
		if (flag == 1) {
			sysuserentity = sysUserService.getUserEmail(validateCode);
		} else {
			sysuserentity = sysUserService.getUserphone(validateCode);
		}
		if (sysuserentity == null) {
			return R.error("邮箱未注册"); // 邮箱/手机未注册
		} else {
			/*String username = sysuserentity.getUsername();*/
			/*if (!username.equals(useraccount)) {
				return R.error("用户名与邮箱不关联");
			}*/
			return R.ok().put("success", "0");
		}
	}

	@RequestMapping(value = "/sendCode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String sendEmail(ServletRequest request, HttpServletRequest req) throws Exception {
		String flag = request.getParameter("flag");
		String validateCode = request.getParameter("validateCode");
		String emailCode = producer.createText();
		SendEmail sendEmail = new SendEmail();
		System.out.println("emailCode:" + emailCode);
		System.out.println("validateCode:" + validateCode);
		System.out.println("flag:" + flag);
		if (Integer.parseInt(flag) == 1) {// 邮箱
			// 返回值一定是一个验证码！！！！
			sendEmail.sendSimpleMail(emailCode, validateCode);
			return "{\"success\":" + "\"" + emailCode + "\"" + "}";
		} else {
			// 这里调用API方法给手机发短信
		}
		return null;
	}

}
