package org.example;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import util.ExcelEntity;
import util.GoldInfo;

import java.util.List;

public class Utils {
    public static ExcelEntity getRandomEntity(List<ExcelEntity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(entityList.size());
        return entityList.get(index);
    }

    public static void updateData(GoldInfo newGoldInfo, VBox vbox) {
        Platform.runLater(() -> {
            if (newGoldInfo!= null) {
                // 更新 goldInfo 对象的数据
                newGoldInfo.setVariety(newGoldInfo.getVariety());
                newGoldInfo.setLatestpri(newGoldInfo.getLatestpri());
                newGoldInfo.setOpenpri(newGoldInfo.getOpenpri());
                newGoldInfo.setMaxpri(newGoldInfo.getMaxpri());
                newGoldInfo.setMinpri(newGoldInfo.getMinpri());
                newGoldInfo.setLimit(newGoldInfo.getLimit());
                newGoldInfo.setYespri(newGoldInfo.getYespri());

                // 更新各个标签显示的内容
                ((Label) vbox.getChildren().get(0)).setText("品种: " + "99%纯度黄金");
                ((Label) vbox.getChildren().get(1)).setText("最新价: " + newGoldInfo.getLatestpri());
                ((Label) vbox.getChildren().get(2)).setText("开盘价: " + newGoldInfo.getOpenpri());
                ((Label) vbox.getChildren().get(3)).setText("最高价: " + newGoldInfo.getMaxpri());
                ((Label) vbox.getChildren().get(4)).setText("最低价: " + newGoldInfo.getMinpri());
                ((Label) vbox.getChildren().get(5)).setText("涨跌幅: " + newGoldInfo.getLimit());
                ((Label) vbox.getChildren().get(6)).setText("昨收价: " + newGoldInfo.getYespri());
            }
        });
    }

    public static JSONObject toJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }
}