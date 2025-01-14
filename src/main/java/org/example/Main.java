package org.example;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
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

    // 布局容器
    private VBox vbox;
    // 显示价格的文本组件
    private Text priceText = new Text();
    // 存储黄金信息的对象
    private GoldInfo goldInfo = new GoldInfo();
    // 存储从 Excel 读取的实体列表
    private List<ExcelEntity> entityList;

    // 调用 API 的密钥
    String apiKey = "21eb7a3312647fe86235543ca530b559";
    // 黄金价格的 API 地址
    String goldapiUrl = "http://web.juhe.cn/finance/gold/shgold";

    @Override
    public void start(Stage primaryStage) {
        // 从 Excel 读取数据到 entityList 中
        entityList = ExcelReaderUtil.readExcel();
        // 显示算命结果的一系列标签
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
        // 设置标签自动换行，以便更好地显示较长的文本
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

        // 按钮，用于随机获取一条答案
        Button randomButton = new Button("===获取一条答案===");
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // 从 entityList 中随机选取一个实体
                ExcelEntity randomEntity = getRandomEntity();
                if (randomEntity!= null) {
                    // 将实体的信息设置到相应的标签上
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
                    // 若未获取到实体，清空标签内容
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
        // 设置舞台标题
        primaryStage.setTitle("黄金与股票价格查询");
        // GoldInfo goldInfo=new GoldInfo();

        // 按钮，用于获取黄金价格
        Button fetchButton = new Button("获取价格");
        fetchButton.setOnAction(event -> {
            try {
                // 调用 API 获取黄金价格
                String goldPrice = HttpUtils.getPriceFromUrl(goldapiUrl + "?v=&key=" + apiKey);
                // 将获取的 JSON 字符串转换为 JSONObject
                JSONObject goldmoney = toJson(goldPrice);
                // 解析 JSON 数据，获取特定的黄金信息
                JSONObject totlegold = goldmoney.getJSONArray("result").getJSONObject(0).getJSONObject("12");
                // 将 JSONObject 转换为 GoldInfo 实体对象
                goldInfo = JsonToEntityUtil.jsonObjectToGoldInfo(totlegold);
                // 更新界面显示的数据
                updateData(goldInfo);
                // priceText.setText(goldInfo.toLable().toString());
                // 以下是模拟获取股票价格的部分，实际中需要替换为真实的 API 调用
                // String stockPrice = getPriceFromUrl("https://example.com/stock-price-api");
                // priceText.setText("黄金价格: " + goldPrice + "\n股票价格: " + stockPrice);
            } catch (IOException e) {
                e.printStackTrace();
                // 若发生异常，显示错误信息
                priceText.setText("获取价格出错，请检查网络或接口配置");
            }
        });

        // 创建显示各个数据的标签，使用实体类的 getter 方法获取对应数据
        Label varietyLabel = new Label("品种: " + goldInfo.getVariety());
        Label latestpriLabel = new Label("最新价: " + goldInfo.getLatestpri());
        Label openpriLabel = new Label("开盘价: " + goldInfo.getOpenpri());
        Label maxpriLabel = new Label("最高价: " + goldInfo.getMaxpri());
        Label minpriLabel = new Label("最低价: " + goldInfo.getMinpri());
        Label limitLabel = new Label("涨跌幅: " + goldInfo.getLimit());
        Label yespriLabel = new Label("昨收价: " + goldInfo.getYespri());
        // 创建垂直布局容器
        vbox = new VBox(10);
        // 将各个组件添加到垂直布局容器中
        vbox.getChildren().addAll(varietyLabel, latestpriLabel, openpriLabel, maxpriLabel, minpriLabel,
                limitLabel, yespriLabel, fetchButton, randomButton,
                titleLabel, contextLabel, wenLabel, gujieLabel, jieLabel, jie1Label, jie2Label,
                jie3Label, jie4Label, jie5Label, jie6Label);
        // 创建场景并设置布局
        Scene scene = new Scene(vbox, 450, 1000);
        // 为场景添加样式表
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        // 将场景设置到舞台并显示
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 将 JSON 字符串转换为 JSONObject 的方法
    public JSONObject toJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    // 更新界面数据的方法，在 JavaFX 应用程序线程中执行
    private void updateData(GoldInfo newGoldInfo) {
        Platform.runLater(() -> {
            if (newGoldInfo!= null) {
                // 更新 goldInfo 对象的数据
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

    // 从 entityList 中随机获取一个 ExcelEntity 的方法
    private ExcelEntity getRandomEntity() {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(entityList.size());
        return entityList.get(index);
    }

    // 主程序入口
    public static void main(String[] args) {
        launch(args);
    }
}