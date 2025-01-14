package util;

public class ExcelEntity {
    private String id;
    private String title;
    private String context;
    private String wen;
    private String gujie;
    private String jie;
    private String jie1;
    private String jie2;
    private String jie3;
    private String jie4;
    private String jie5;
    private String jie6;

    // 构造函数
    public ExcelEntity(String id, String title, String context, String wen, String gujie, String jie, String jie1, String jie2, String jie3, String jie4, String jie5, String jie6) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.wen = wen;
        this.gujie = gujie;
        this.jie = jie;
        this.jie1 = jie1;
        this.jie2 = jie2;
        this.jie3 = jie3;
        this.jie4 = jie4;
        this.jie5 = jie5;
        this.jie6 = jie6;
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getWen() {
        return wen;
    }

    public void setWen(String wen) {
        this.wen = wen;
    }

    public String getGujie() {
        return gujie;
    }

    public void setGujie(String gujie) {
        this.gujie = gujie;
    }

    public String getJie() {
        return jie;
    }

    public void setJie(String jie) {
        this.jie = jie;
    }

    public String getJie1() {
        return jie1;
    }

    public void setJie1(String jie1) {
        this.jie1 = jie1;
    }

    public String getJie2() {
        return jie2;
    }

    public void setJie2(String jie2) {
        this.jie2 = jie2;
    }

    public String getJie3() {
        return jie3;
    }

    public void setJie3(String jie3) {
        this.jie3 = jie3;
    }

    public String getJie4() {
        return jie4;
    }

    public void setJie4(String jie4) {
        this.jie4 = jie4;
    }

    public String getJie5() {
        return jie5;
    }

    public void setJie5(String jie5) {
        this.jie5 = jie5;
    }

    public String getJie6() {
        return jie6;
    }

    public void setJie6(String jie6) {
        this.jie6 = jie6;
    }
}