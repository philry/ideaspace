package com.itany.nmms.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.mvc.annotation.RequestMapping;

//RequestMapping表示访问的请求
//当RequestMapper在类体上时,表示访问该类中的任意方法都必须在请求中加上该值
@RequestMapping("/code")
public class CodeAction {
	
	@RequestMapping("/show")
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Random random = new Random();
		
		//在内存中创建一个画板
		BufferedImage image = new BufferedImage(55, 30, BufferedImage.TYPE_INT_RGB);
		
		//获取画笔
		Graphics graphics = image.getGraphics();
		
		//填充矩形背景
		graphics.fillRect(0, 0, 55, 30);
		
		//设置画笔颜色
		//随机生成一个颜色
		graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
		
		//设置字体样式
		graphics.setFont(new Font("宋体", Font.BOLD+Font.ITALIC, 20));
		
		//生成内容
		//生成一个随机的内容
		//希望内容包含0-9a-z
		String s = "0123456789qwertyuiopasdfghjklzxcvbnm";
		StringBuffer code = new StringBuffer();
		for(int i = 0; i < 4; i++){
			code.append(s.charAt(random.nextInt(s.length())));
		}
		graphics.drawString(code.toString(), 5, 20);
		request.getSession().setAttribute("code", code.toString());
		System.out.println("验证码:"+code.toString());
		
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		
		
	}

}
