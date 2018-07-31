package net.chenlin.dp.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.chenlin.dp.common.utils.ImageCodeUtils;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.common.utils.SendEmail;
import net.chenlin.dp.common.utils.ShiroUtils;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注册controller
 *
 * @author RickyZZQ
 * @email 877853951@qq.com
 * @url
 * @date 2018年6月4日 下午2:12:37
 */
@RestController
@RequestMapping("/sys")
public class SysRegistController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
    private Producer producer;
	/*
	 * @Autowired private Producer
	 * producer;//=ImageCodeUtils.getInstance().getProducer();
	 */

	/**
	 * 验证码
	 * 
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	/*
	 * @RequestMapping("/captcha.jpg") public void captcha(HttpServletRequest
	 * request, HttpServletResponse response)throws ServletException, IOException {
	 * response.setHeader("Cache-Control", "no-store, no-cache");
	 * response.setContentType("image/jpeg");
	 * 
	 * //生成文字验证码 String text = producer.createText(); //生成图片验证码 BufferedImage image
	 * = producer.createImage(text); //保存到shiro session
	 * ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text); //
	 * HttpSession session = request.getSession(); //System.out.println("session = "
	 * + session.getId()); //session.setAttribute(Constants.KAPTCHA_SESSION_KEY,
	 * text); ServletOutputStream out = response.getOutputStream();
	 * ImageIO.write(image, "jpg", out); }
	 */
	/**
	 * 注册\
	 * 
	 */
	@SuppressWarnings("null")
	@SysLog("注册")
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public R regist(String username, String password, String check_user_psd, String usertrname, /*String ID_card,*/
			String email, String authcode, String identity) {		
		/*System.out.println(username);
		System.out.println(password);
		System.out.println(check_user_psd);
		System.out.println(user_tr_name);
		System.out.println(ID_card);
		System.out.println(email);
		System.out.println(authcode);
		System.out.println(identity);*/
		// SysUserEntity user = new SysUserEntity();
		SysUserEntity user = sysUserService.getByUserName(username);
		SysUserEntity existEmail = sysUserService.getUserEmail(email);

		if (existEmail != null) {
			return R.error("邮箱已注册");
		}

		if (user != null) {
			return R.error("用户名已存在");
		}
		if(password.length() <6) {
			return R.error("密码不能少于6位");
		}

		if (!password.equals(check_user_psd)) {
			return R.error("两次密码输入不一致");
		}

		password = MD5Utils.encrypt(username,password);
		System.out.println("注册："+password);
		if (user == null) {
			user = new SysUserEntity();
			user.setUsername(username);
			user.setPassword(password);
			user.setUsertrname(usertrname);
			/*user.setID_card(ID_card);*/
			user.setEmail(email);
			user.setIdentity(identity);
		}
		return sysUserService.regist(user);
	}
	
	@SysLog("发送邮件验证码")
	@RequestMapping(value = "/recvCode", method = RequestMethod.POST)
	public String recvCode(String email) {
		SendEmail sendEmail = new SendEmail();
		String emailCode = producer.createText();
		try {
			sendEmail.sendSimpleMail(emailCode,email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"success\":"+"\""+emailCode+"\""+"}";
	}
}
