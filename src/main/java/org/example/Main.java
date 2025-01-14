package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.*;
import javafx.event.EventHandler;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;
import util.GoldInfo;
import util.JsonToEntityUtil;
import util.ExcelEntity;
import util.ExcelReaderUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private VBox vbox;
    private Text priceText = new Text();
    private GoldInfo goldInfo=new GoldInfo();
    private List<ExcelEntity> entityList;

    String apiKey = "21eb7a3312647fe86235543ca530b559";
    String goldapiUrl = "http://web.juhe.cn/finance/gold/shgold";

    @Override
    public void start(Stage primaryStage) {
        entityList = ExcelReaderUtil.readExcel();
        //显示算命
        Label resultLabel = new Label();
        Label idLabel = new Label();
        Label titleLabel = new Label();
        Label contextLabel = new Label();
        Label wenLabel = new Label();
        Label gujieLabel = new Label();
        Label jieLabel = new Label();
        Label jie1Label = new Label();
        Label jie2Label = new Label();
        Label jie3Label = new Label();
        Label jie4Label = new Label();
        Label jie5Label = new Label();
        Label jie6Label = new Label();
        // 设置标签自动换行
        idLabel.setWrapText(true);
        titleLabel.setWrapText(true);
        contextLabel.setWrapText(true);
        wenLabel.setWrapText(true);
        gujieLabel.setWrapText(true);
        jieLabel.setWrapText(true);
        jie1Label.setWrapText(true);
        jie2Label.setWrapText(true);
        jie3Label.setWrapText(true);
        jie4Label.setWrapText(true);
        jie5Label.setWrapText(true);
        jie6Label.setWrapText(true);

        Button randomButton = new Button("===获取一条答案===");
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ExcelEntity randomEntity = getRandomEntity();
                if (randomEntity!= null) {
                    titleLabel.setText("抽取: " + randomEntity.getTitle());
                    contextLabel.setText("方位吉凶: " + randomEntity.getContext());
                    wenLabel.setText("" + randomEntity.getWen());
                    gujieLabel.setText("签文: " + randomEntity.getGujie());
                    jieLabel.setText("" + randomEntity.getJie());
                    jie1Label.setText(" " + randomEntity.getJie1());
                    jie2Label.setText(" " + randomEntity.getJie2());
                    jie3Label.setText(" " + randomEntity.getJie3());
                    jie4Label.setText(" " + randomEntity.getJie4());
                    jie5Label.setText(" " + randomEntity.getJie5());
                    jie6Label.setText(" " + randomEntity.getJie6());
                } else {
                    titleLabel.setText("");
                    contextLabel.setText("");
                    wenLabel.setText("");
                    gujieLabel.setText("");
                    jieLabel.setText("");
                    jie1Label.setText("");
                    jie2Label.setText("");
                    jie3Label.setText("");
                    jie4Label.setText("");
                    jie5Label.setText("");
                    jie6Label.setText("");
                }
            }
        });
        primaryStage.setTitle("黄金与股票价格查询");
//        GoldInfo goldInfo=new GoldInfo();
        Button fetchButton = new Button("获取价格");
        fetchButton.setOnAction(event -> {
            try {
                // 这里获取黄金价格的接口
                String goldPrice = getPriceFromUrl(goldapiUrl+"?v=&key="+apiKey);
                JSONObject goldmoney=toJson(goldPrice);
                JSONObject totlegold=goldmoney.getJSONArray("result").getJSONObject(0).getJSONObject("12");
                // 将JSONObject转换为实体类对象
                goldInfo = JsonToEntityUtil.jsonObjectToGoldInfo(totlegold);
                updateData(goldInfo);
                //priceText.setText(goldInfo.toLable().toString());
                // 模拟获取股票价格的接口（实际中替换为真实可用的）
//                String stockPrice = getPriceFromUrl("https://example.com/stock-price-api");
//                priceText.setText("黄金价格: " + goldPrice + "\n股票价格: " + stockPrice);
            } catch (IOException e) {
                e.printStackTrace();
                priceText.setText("获取价格出错，请检查网络或接口配置");
            }
        });

        // 创建显示各个数据的标签，使用实体类的getter方法获取对应数据
        Label varietyLabel = new Label("品种: " + goldInfo.getVariety());
        Label latestpriLabel = new Label("最新价: " + goldInfo.getLatestpri());
        Label openpriLabel = new Label("开盘价: " + goldInfo.getOpenpri());
        Label maxpriLabel = new Label("最高价: " + goldInfo.getMaxpri());
        Label minpriLabel = new Label("最低价: " + goldInfo.getMinpri());
        Label limitLabel = new Label("涨跌幅: " + goldInfo.getLimit());
        Label yespriLabel = new Label("昨收价: " + goldInfo.getYespri());
        vbox = new VBox(10);
        vbox.getChildren().addAll(varietyLabel, latestpriLabel, openpriLabel, maxpriLabel, minpriLabel,
                limitLabel, yespriLabel,fetchButton,randomButton,
                titleLabel, contextLabel, wenLabel, gujieLabel, jieLabel, jie1Label, jie2Label,
                jie3Label, jie4Label, jie5Label, jie6Label);
        Scene scene = new Scene(vbox, 450, 1000);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getPriceFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine())!= null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }
    public JSONObject toJson(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }
    // 更新界面数据的方法
    private void updateData(GoldInfo newGoldInfo) {
        Platform.runLater(() -> {
            if (newGoldInfo!= null) {
                goldInfo.setVariety(newGoldInfo.getVariety());
                goldInfo.setLatestpri(newGoldInfo.getLatestpri());
                goldInfo.setOpenpri(newGoldInfo.getOpenpri());
                goldInfo.setMaxpri(newGoldInfo.getMaxpri());
                goldInfo.setMinpri(newGoldInfo.getMinpri());
                goldInfo.setLimit(newGoldInfo.getLimit());
                goldInfo.setYespri(newGoldInfo.getYespri());

                // 更新各个标签显示的内容
                ((Label) vbox.getChildren().get(0)).setText("品种: " + "99%纯度黄金");
                ((Label) vbox.getChildren().get(1)).setText("最新价: " + goldInfo.getLatestpri());
                ((Label) vbox.getChildren().get(2)).setText("开盘价: " + goldInfo.getOpenpri());
                ((Label) vbox.getChildren().get(3)).setText("最高价: " + goldInfo.getMaxpri());
                ((Label) vbox.getChildren().get(4)).setText("最低价: " + goldInfo.getMinpri());
                ((Label) vbox.getChildren().get(5)).setText("涨跌幅: " + goldInfo.getLimit());
                ((Label) vbox.getChildren().get(6)).setText("昨收价: " + goldInfo.getYespri());
            }
        });
    }
    private ExcelEntity getRandomEntity() {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(entityList.size());
        return entityList.get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }
}