package cn.qaq.qqrobota2srcon.service;

import cn.qaq.qqrobota2srcon.config.GlobalConfig;
import cn.qaq.qqrobota2srcon.utils.QQPojo;
import cn.qaq.qqrobota2srcon.utils.QQresponse;
import cn.qaq.qqrobota2srcon.utils.TcpTools;
import cn.qaq.qqrobota2srcon.utils.UdpServer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * @program: QaQCloud
 * @description: 服务
 * @author: QAQ
 * @create: 2019-09-03 10:17
 **/
@Service
public class QQService {


    @Autowired
    private GlobalConfig config;
    public String getServerPlayers(String ip) throws IOException {

        JSONArray jsonArray=UdpServer.getPlayers(ip);
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<jsonArray.size();i++)
        {
            stringBuilder.append("当前玩家:");
            stringBuilder.append(jsonArray.size());
            stringBuilder.append("个\n");
            stringBuilder.append(jsonArray.getJSONObject(i).getString("name"));
            stringBuilder.append("     ");
            int time=jsonArray.getJSONObject(i).getInt("time");
            if(time>3600)
            {
                stringBuilder.append(time/3600);
                stringBuilder.append("h");
                stringBuilder.append(time/60%60);
                stringBuilder.append("m");
                stringBuilder.append(time%60);
                stringBuilder.append("s");
            }else if(time>60){
                stringBuilder.append(time/60);
                stringBuilder.append("m");
                stringBuilder.append(time%60);
                stringBuilder.append("s");
            }else {
                stringBuilder.append(time);
                stringBuilder.append("s");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public String getServerInfo(String ip)
    {
        JSONObject jsonObject1=UdpServer.getServers(ip);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("服务器名称: ");
        stringBuilder.append(jsonObject1.getString("name"));
        stringBuilder.append("\n当前地图: ");
        stringBuilder.append(jsonObject1.getString("map"));
        stringBuilder.append("\n当前人数/Max: ");
        stringBuilder.append(jsonObject1.getString("players"));
        stringBuilder.append("\n延迟：");
        stringBuilder.append(jsonObject1.getString("time"));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    public QQresponse msgHandle(QQPojo qqPojo)throws Exception
    {
        qqPojo.setIsmanager(config.isAuth(String.valueOf(qqPojo.getSender().getUser_id())));
        HashMap<String,GlobalConfig.server> servers=config.getServerMap();
        //下面开始分析....
        if(qqPojo.getMessage()==null||!(qqPojo.getMessage().contains("/"))) return null;
        if(qqPojo.getMessage().startsWith("/players"))
        {
            if(!qqPojo.getMessage().equals("/players")) return new QQresponse(getServerPlayers(qqPojo.getMessage()
                    .replace("/players ","")
                    .replace(" ","")));
            StringBuilder stringBuilder=new StringBuilder();
            for(HashMap.Entry<String,GlobalConfig.server> entry:servers.entrySet())
            {//遍历服务器信息
                stringBuilder.append(entry.getKey());
                stringBuilder.append(" ");
                stringBuilder.append(getServerPlayers(entry.getValue().getIp()));
            }
            return  new QQresponse(stringBuilder.toString());

        }else if (qqPojo.getMessage().equals("/list"))
        {
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("服务器列表：\n");
            for(HashMap.Entry<String,GlobalConfig.server> entry:servers.entrySet())
            {//遍历服务器信息
                stringBuilder.append(entry.getKey());
                stringBuilder.append("\n");
            }
            return  new QQresponse(stringBuilder.toString());
        }else  if(qqPojo.getMessage().equals("/server"))
        {
            StringBuilder stringBuilder=new StringBuilder();
            for(HashMap.Entry<String,GlobalConfig.server> entry:servers.entrySet())
            {//遍历服务器信息
                stringBuilder.append(entry.getKey());
                stringBuilder.append(":\n");
                stringBuilder.append(getServerInfo(entry.getValue().getIp()));
            }
            return  new QQresponse(stringBuilder.toString());
        }
        else if(qqPojo.getMessage().startsWith("/connect "))
        {
            return new QQresponse(getServerInfo(qqPojo.getMessage()
                    .replace("/connect ","")
                    .replace(" ","")));
        }else if(qqPojo.isIsmanager()&&qqPojo.getMessage().startsWith("/exec ")){
            String name=qqPojo.getMessage().split(" ")[1];
            TcpTools tools =new TcpTools();
            tools.initTcp(servers.get(name).getIp());
            String res=tools.send(qqPojo.getMessage().replace("/exec ","").replace(name,""),servers.get(name).getPassword());
            tools.closeTcp();
            return new QQresponse(res);
        }
        return null;
    }
}
