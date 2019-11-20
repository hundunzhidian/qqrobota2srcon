package cn.qaq.qqrobota2srcon.controller;

import cn.qaq.qqrobota2srcon.service.QQService;
import cn.qaq.qqrobota2srcon.utils.QQPojo;
import cn.qaq.qqrobota2srcon.utils.QQresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: QaQCloud
 * @description: qq机器人处理
 * @author: QAQ
 * @create: 2019-09-03 10:15
 **/

@RestController
@RequestMapping("/")
public class QQController {

    @Autowired
    private QQService service;

    @PostMapping("/")
    public QQresponse msgHandle(@RequestBody QQPojo qqPojo)
    {
        try {
            //log.debug("JSON:"+ JSONObject.fromObject(qqPojo).toString());
            return service.msgHandle(qqPojo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

            return new QQresponse("【ERROR】消息处理出现异常:"+e.getLocalizedMessage());
        }
    }

}
