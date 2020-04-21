package com.neuedu.controller;

import com.neuedu.entity.User;
import com.neuedu.framework.HisConstants;
import com.neuedu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  9:08 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 登陆
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录
     * @param userName
     * @param password
     * @param validaCode
     * @param session
     * @return
     */
    @RequestMapping("login")
    public Map login(String userName, String password, String validaCode, HttpSession session) {
        boolean isLogin = false;
        Map result = new HashMap();

        String sessionVerifyCode = (String) session.getAttribute(HisConstants.VERIFY_CODE);
        if(sessionVerifyCode == null){
            sessionVerifyCode = "";
        }
        if(!sessionVerifyCode.equalsIgnoreCase(validaCode)){
            result.put("msg", "验证码不正确");
        }else{
            //校验登录的用户名、密码是否正确
            User user = loginService.login(userName, password);
            if (user != null) {
                //在session中记录 登录状态
                session.setAttribute(HisConstants.LOGIN_USER, user);
                isLogin = true;
            } else {
                result.put("msg", "用户名或密码不正确");
            }
        }

        result.put("success", isLogin);

        return result;
    }


    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping("getUserInfo")
    public User getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(HisConstants.LOGIN_USER);

        user.setPassword(null);
        return user;
    }


    /**
     * 获取用户信息
     *
     * @param
     * @return
     */
    @RequestMapping("getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        try {

            int width = 200;
            int height = 69;

            //缓冲区 用于在内存中存储 生成的图片
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //生成对应宽高的初始图片
            String randomText = drawRandomText(width, height, verifyImg); //随机生成一个字符串

            request.getSession().setAttribute("verifyCode", randomText);

            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出
            ImageIO.write(verifyImg, "png", os);//输出图片流
            os.flush();
            os.close();//关闭流

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public static String drawRandomText(int width, int height, BufferedImage verifyImg) {

        Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();

        graphics.setColor(Color.WHITE);//设置画笔颜色-验证码背景色

        graphics.fillRect(0, 0, width, height);//填充背景
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 40));

        //数字和字母的组合

        String baseNumLetter = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

        StringBuffer sBuffer = new StringBuffer();

        int x = 10;  //旋转原点的 x 坐标

        String ch = "";

        Random random = new Random();

        for (int i = 0; i < 4; i++) {

            graphics.setColor(getRandomColor());

            //设置字体旋转角度
            int degree = random.nextInt() % 30;  //角度小于30度
            int dot = random.nextInt(baseNumLetter.length());
            ch = baseNumLetter.charAt(dot) + "";

            sBuffer.append(ch);

            //正向旋转
            graphics.rotate(degree * Math.PI / 180, x, 45);
            graphics.drawString(ch, x, 45);

            //反向旋转
            graphics.rotate(-degree * Math.PI / 180, x, 45);

            x += 48;

        }

        //画干扰线
        for (int i = 0; i < 6; i++) {

            // 设置随机颜色
            graphics.setColor(getRandomColor());

            // 随机画线
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));

        }

        //添加噪点

        for (int i = 0; i < 30; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.setColor(getRandomColor());
            graphics.fillRect(x1, y1, 2, 2);

        }

        return sBuffer.toString();

    }

    /**
     * 随机取色
     */

    private static Color getRandomColor() {

        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
        ran.nextInt(256), ran.nextInt(256));
        return color;
    }

    @RequestMapping("logout")
    public void logout( HttpSession session ,HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("login.html");
    }
}
