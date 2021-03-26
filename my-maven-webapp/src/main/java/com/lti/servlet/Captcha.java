package com.lti.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/captcha.jpg")
public class Captcha extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		
		String str ="abcdeghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String captchatext = "";
		for(int i=0;i<5;i++) {
			int rno = (int)(Math.random()*str.length());
			captchatext += str.charAt(rno);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("captchaText", captchatext);
		
		BufferedImage img = new BufferedImage(125,75,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, 125, 75);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Harrington", Font.BOLD,32));
		g.drawString(captchatext,10,50);
		
		ImageIO.write(img,"jpeg",out);
	}


}
