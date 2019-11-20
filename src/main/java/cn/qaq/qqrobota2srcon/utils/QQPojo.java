package cn.qaq.qqrobota2srcon.utils;

/**
 * @program: QaQCloud
 * @description: QQ机器人对象
 * @author: QAQ
 * @create: 2019-09-02 11:14
 **/
public class QQPojo {
    private String post_type;
    private String message_type;
    private String sub_type;
    private String message_id;
    private String group_id;
    private long user_id;
    private String message;
    private String raw_message;
    private String font;
    private Sender sender;
    private Anonymous anonymous;

    private boolean ismanager=false;
    public boolean isIsmanager() {
        return ismanager;
    }

    public void setIsmanager(boolean ismanager) {
        this.ismanager = ismanager;
    }
    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRaw_message() {
        return raw_message;
    }

    public void setRaw_message(String raw_message) {
        this.raw_message = raw_message;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(String user_id, String nickname, String sex, String age) {
        this.sender = new Sender(user_id,nickname,sex,age);
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String id, String name, String flag) {
        this.anonymous = new Anonymous(id,name,flag);
    }

    public class Anonymous{

        private String id;
        private String name;
        private String flag;

        public Anonymous(String id, String name, String flag) {
            this.id = id;
            this.name = name;
            this.flag = flag;
        }
        public Anonymous(){}
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
    public class Sender{
        private String user_id;
        private String nickname;
        private String sex;
        private String age;

        public Sender() {
        }

        public Sender(String user_id, String nickname, String sex, String age) {
            this.user_id = user_id;
            this.nickname = nickname;
            this.sex = sex;
            this.age = age;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
