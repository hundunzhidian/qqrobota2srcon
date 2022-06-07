package cn.qaq.qqrobota2srcon.controller;

import cn.qaq.qqrobota2srcon.service.QQService;
import cn.qaq.qqrobota2srcon.utils.QQPojo;
import cn.qaq.qqrobota2srcon.utils.QQresponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/")
public class QQController {

    @Autowired
    private QQService service;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/")
    public QQresponse msgHandle(@RequestBody QQPojo qqPojo)
    {
        try {
            return service.msgHandle(qqPojo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

            return new QQresponse("【ERROR】消息处理出现异常:"+e.getLocalizedMessage());
        }
    }

}
