//package cn.smallshark.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.internet.MimeMessage;
//
//
///**
// * 用于警报和出入库的邮箱通知
// *
// */
//@Component
//public class SendEmail {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    //从配置文件中获取到自己的邮箱地址
//    @Value("${spring.mail.username}")
//    private  String sendusername;
//
//    /**
//     * @param usereamil 被发送用户的邮箱
//     * @param eamiltitle   邮箱标题
//     * @param msg   发送的信息
//     * @throws Exception
//     */
//    public void sendAttachmentMaik(String usereamil, String eamiltitle, String msg) throws Exception {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        //发送者
//        helper.setFrom(sendusername);
//        //发送给用户的邮箱账号
//        helper.setTo(usereamil);
//        //标题
//        helper.setSubject(eamiltitle);
//        //内容
//        helper.setText(msg);
//        //发送
//        javaMailSender.send(mimeMessage);
//    }
//}
