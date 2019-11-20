package cn.qaq.qqrobota2srcon.utils;

/**
 * @program: QaQCloud
 * @description: qq返回对象
 * @author: QAQ
 * @create: 2019-09-03 10:33
 **/
public class QQresponse {
    private String reply;
    private boolean auto_escape;

    private boolean at_sender;
    private boolean delete;
    private boolean kick;
    private boolean ban;
    private long ban_duration;

    public QQresponse(String reply, boolean auto_escape, boolean at_sender, boolean delete, boolean kick, boolean ban, long ban_duration) {
        this.reply = reply;
        this.auto_escape = auto_escape;
        this.at_sender = at_sender;
        this.delete = delete;
        this.kick = kick;
        this.ban = ban;
        this.ban_duration = ban_duration;
    }

    //这个最常用
    public QQresponse(String reply, boolean auto_escape, boolean at_sender) {
        this.reply = reply;
        this.auto_escape = auto_escape;
        this.at_sender = at_sender;
    }

    public QQresponse(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isAuto_escape() {
        return auto_escape;
    }

    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }

    public boolean isAt_sender() {
        return at_sender;
    }

    public void setAt_sender(boolean at_sender) {
        this.at_sender = at_sender;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isKick() {
        return kick;
    }

    public void setKick(boolean kick) {
        this.kick = kick;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public long getBan_duration() {
        return ban_duration;
    }

    public void setBan_duration(long ban_duration) {
        this.ban_duration = ban_duration;
    }
}
