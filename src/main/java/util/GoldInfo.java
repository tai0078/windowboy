package util;

import javafx.scene.control.Label;

public class GoldInfo {
    private String variety;
    private String latestpri;
    private String openpri;
    private String maxpri;
    private String minpri;
    private String limit;
    private String yespri;
    private String totalvol;
    private String time;

    // 生成对应的getter和setter方法
    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getLatestpri() {
        return latestpri;
    }

    public void setLatestpri(String latestpri) {
        this.latestpri = latestpri;
    }

    public String getOpenpri() {
        return openpri;
    }

    public void setOpenpri(String openpri) {
        this.openpri = openpri;
    }

    public String getMaxpri() {
        return maxpri;
    }

    public void setMaxpri(String maxpri) {
        this.maxpri = maxpri;
    }

    public String getMinpri() {
        return minpri;
    }

    public void setMinpri(String minpri) {
        this.minpri = minpri;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getYespri() {
        return yespri;
    }

    public void setYespri(String yespri) {
        this.yespri = yespri;
    }

    public String getTotalvol() {
        return totalvol;
    }

    public void setTotalvol(String totalvol) {
        this.totalvol = totalvol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // 创建显示各个数据的标签，使用实体类的getter方法获取对应数据
//    public Label[] toLable(){
//        Label[] retuLabel=new Label[9];
//        retuLabel[0] = new Label("品种: " + variety );
//        retuLabel[1] = new Label("最新价: " + latestpri );
//        retuLabel[2] = new Label("开盘价: " + openpri );
//        retuLabel[3] = new Label("最高价: " + maxpri );
//        retuLabel[4] = new Label("最低价: " + minpri );
//        retuLabel[5] = new Label("涨跌幅: " + limit );
//        retuLabel[6] = new Label("昨收价: " + yespri );
//        retuLabel[7] = new Label("总成交量: " + totalvol );
//        retuLabel[8] = new Label("更新时间: " + time );
//        return retuLabel;
//
//    }

}