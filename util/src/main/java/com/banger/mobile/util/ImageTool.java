
package com.banger.mobile.util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.Color;
import java.util.Random;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;
import javax.imageio.ImageIO;



import java.io.IOException;

/**
 * @author zhangxiang
 * @version $Id: ImageTool.java,v 0.1 2011-6-20 下午04:21:27 zhangxiang Exp $
 */
public class ImageTool {
    private static Random random = new Random();

    public ImageTool() {
    }

    public static Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static void outputImage(HttpSession session, HttpServletResponse response)
                                                                                     throws IOException {

        BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, 60, 20);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(60);
            int y = random.nextInt(20);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String temp = String.valueOf(random.nextInt(10));
            randCode.append(temp);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random
                .nextInt(110)));
            g.drawString(temp, 13 * i + 6, 16);
        }
        session.setAttribute("randCode", randCode.toString());

        g.dispose();
        OutputStream out = response.getOutputStream();

        response.reset();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    public static void imagePut(HttpSession session, OutputStream out, int pWidth, int pHeight,
                                int pSize) throws IOException {

        BufferedImage image = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, pWidth, pHeight);

        g.setFont(new Font("Times New Roman", Font.PLAIN, pSize));

        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 18; i++) {
            int x = random.nextInt(60);
            int y = random.nextInt(20);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String temp = String.valueOf(random.nextInt(10));
            randCode.append(temp);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random
                .nextInt(110)));
            g.drawString(temp, 13 * i + 6, 16);
        }
        /*cookie=new Cookie("randCode",randCode.toString());
        cookie.setMaxAge(-1);
        cookie.setComment("生成验证码");
        cookie.setPath("/");
        response.addCookie(cookie); */
        
        session.setAttribute("randCode", randCode.toString());
        g.dispose();
        ImageIO.write(image, "jpg", out);

        //System.out.println("sessionID："+session.toString()+"； 产生的验证码："+session.getAttribute("randCode"));

    }

    public static void imagePutOneColor(HttpSession session, OutputStream out, int pWidth,
                                        int pHeight, int pSize) throws IOException {

        BufferedImage image = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, 60, 20);

        g.setFont(new Font("Times New Roman", Font.TRUETYPE_FONT, pSize));

        g.setColor(getRandColor(200, 250));
        for (int i = 0; i < 18; i++) {
            int x = random.nextInt(60);
            int y = random.nextInt(20);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String temp = String.valueOf(random.nextInt(10));
            randCode.append(temp);
            g.setColor(new Color(0, 0, 0));
            g.drawString(temp, 13 * i + 6, 16);
        }
        session.setAttribute("randCode", randCode.toString());

        g.dispose();
        ImageIO.write(image, "jpg", out);
    }

    public static void outputImage(String sessionName, HttpSession session,
                                   HttpServletResponse response) throws IOException {

        BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, 60, 20);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(60);
            int y = random.nextInt(20);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String temp = String.valueOf(random.nextInt(10));
            randCode.append(temp);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random
                .nextInt(110)));
            g.drawString(temp, 13 * i + 6, 16);
        }
        session.setAttribute(sessionName, randCode.toString());
        g.dispose();
        response.reset();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);

        out.flush();
        out.close();
    }
}
