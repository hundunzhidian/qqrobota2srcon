package cn.qaq.qqrobota2srcon.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: qqrobota2srcon
 * @description: 配置加载器
 * @author: QAQ
 * @create: 2019-11-20 09:16
 **/
@Component
@Slf4j
public class GlobalConfig {

    private HashMap<String,server> serverMap=new HashMap<String,server>();//
    @Value("#{'${config.server.qq}'.split(';')}")
    private List<String> manaQQs=new ArrayList<>();//这个是管理员qq号，为空则无需授权使用rcon命令

    @Value("#{'${config.server.ip}'.split(';')}")
    private List<String> ips;

    @Value("#{'${config.server.password}'.split(';')}")
    private List<String> passwords;
    @Value("#{'${config.server.name}'.split(';')}")
    private List<String> names;

    @Autowired
    private ConfigurableApplicationContext context;

    public HashMap<String, server> getServerMap() {
        return serverMap;
    }

    public class server
    {
        private String ip;
        private String password;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public server(String ip, String password) {
            this.ip = ip;
            this.password = password;
        }
    }
    public server getServer(String name)
    {
        return  serverMap.get(name);
    }
    public boolean isAuth(String qq)
    {
        if(manaQQs==null||manaQQs.size()==0) return true;
        else return manaQQs.contains(qq);
    }
    @PostConstruct
    public void Init()
    {
        log.info("准备加载配置文件信息.....");
        try {
            for(Integer i=0;i<ips.size();i++)
            {
                serverMap.put(names.get(i),new server(ips.get(i),passwords.get(i)));
            }
            log.info("配置加载成功....已注入"+serverMap.size()+"个服务器信息");
            log.info("服务器启动成功，等待酷Q消息......");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("初始化配置出现异常！请检查配置文件");
            e.printStackTrace();
            context.close();
        }
    }
}
