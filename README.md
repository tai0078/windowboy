#  **黄金与股票价格查询及抽签小程序** 

####  **项目概述** 
本项目是一个基于 JavaFX 的桌面应用程序，具备黄金价格实时查询以及从 Excel 文件中随机抽取签文信息的功能。用户通过简单的交互操作，即可获取黄金的最新价格动态和有趣的抽签结果。

##   **功能特性** 
黄金价格查询：通过调用聚合数据提供的黄金价格 API，用户点击 “获取价格” 按钮，即可实时获取黄金的品种、最新价、开盘价、最高价、最低价、涨跌幅、昨收价等关键信息，并在界面中直观展示。
抽签功能：预先将签文数据整理存储在 Excel 文件中，用户点击 “=== 获取一条答案 ===” 按钮，程序会从 Excel 文件读取的数据列表中随机抽取一条签文信息，包括抽取内容、方位吉凶、签文解释等详细信息，并展示在界面相应位置。

####  技术实现
前端界面：使用 JavaFX 构建用户界面，通过 VBox、Label、Button 等组件进行布局和交互设计。利用 Scene 和 Stage 搭建窗口框架，实现界面的展示与交互。
数据获取：通过 HttpURLConnection 与黄金价格 API 进行交互，发送 GET 请求获取数据。使用 BufferedReader 读取 API 返回的 JSON 格式数据，并通过 JSONObject 和 JSONArray 对数据进行解析和处理。
数据解析：借助 util.JsonToEntityUtil 工具类，将从 API 获取并解析后的 JSON 数据转换为 GoldInfo 实体类对象，方便在程序中进行数据的传递和处理。
Excel 数据读取：利用 util.ExcelReaderUtil 工具类，基于 Apache POI 库实现对 Excel 文件的读取操作。将 Excel 文件中的数据读取并转换为 ExcelEntity 实体类对象列表，以便后续抽签功能的实现。
随机抽取：通过 Random 类生成随机数，从存储签文信息的 ExcelEntity 列表中随机抽取一条签文，实现抽签的随机性。

##  项目结构

####   **org.example** 包：
 **Main.java** ：程序的主入口，继承自 Application 类，负责启动 JavaFX 应用程序，并调用 SceneFactory 创建和设置主场景。
 **SceneFactory.java** ：采用工厂模式，负责创建和配置 JavaFX 场景。在 createScene 方法中，初始化界面组件、设置组件的事件处理逻辑，并将组件添加到场景中。

####   **util** 包：
 **GoldInfo.java** ：黄金信息的实体类，包含黄金价格相关的各种属性及对应的 getter 和 setter 方法。
 **JsonToEntityUtil.java** ：提供将 JSONObject 转换为 GoldInfo 实体对象的方法。
 **ExcelEntity.java** ：签文信息的实体类，包含签文相关的各种属性及对应的 getter 和 setter 方法。
 **ExcelReaderUtil.java** ：提供读取 Excel 文件，并将文件内容转换为 ExcelEntity 列表的方法。

##   **资源文件：** 
 **style.css** ：用于定义 JavaFX 界面的样式，包括组件的颜色、字体、间距等外观属性，提升用户界面的视觉效果。
##   **使用方法** 
确保本地环境已安装 Java Development Kit（JDK），建议使用 JDK 8 及以上版本。
从 GitHub 仓库下载项目源代码，解压到本地目录。
打开命令行工具，切换到项目根目录。
编译项目：如果使用 Maven 构建工具，执行 mvn clean install 命令；如果手动编译，使用 javac 命令编译源文件。
运行项目：若使用 Maven，执行 mvn javafx:run 命令；若手动编译，找到编译后的主类 Main，使用 java -jar 命令运行项目。
启动程序后，在图形界面中：
点击 “获取价格” 按钮，即可获取并显示最新的黄金价格信息。
点击 “=== 获取一条答案 ===” 按钮，即可随机抽取并显示一条签文信息。
