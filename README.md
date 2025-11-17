## 移动公司网上营业厅系统

一个基于 **Vue.js + Spring Cloud Alibaba** 的网上营业厅示例项目，覆盖开户、费用缴纳、报修/投诉等核心业务场景，前后端完全解耦，后端按照微服务划分为多个 Module，每个服务拥有独立的数据源与接口。

### 仓库结构

```
online-service-portal
├── frontend/               # Vite + Vue3 前端
├── backend/                # Spring Cloud Alibaba 多模块工程
│   ├── account-service/    # 开户服务，独立 H2 库 account-db
│   ├── billing-service/    # 计费服务，独立 H2 库 billing-db
│   ├── support-service/    # 投诉/维修服务，独立 H2 库 support-db，会 HTTP 调用 account-service
│   └── gateway-service/    # API 网关，统一出口
└── docs/                   # 接口表、Postman Collection 等
```

### 前端（Vue.js）

- 使用 `vue-router` 实现仪表盘、开户中心、缴费中心、投诉/维修等页面。
- `axios` 统一封装 HTTP 客户端，通过 `.env` 中的 `VITE_API_GATEWAY` 指向网关。
- 表单联动 + 表格视图，方便演示业务流程。

#### 启动

```bash
cd frontend
npm install
npm run dev
```

构建产物：

```bash
npm run build
```

### 后端（Spring Cloud Alibaba）

- 采用 Maven 多模块聚合工程（根 `pom` 负责依赖管理）。
- 每个微服务都使用 Spring Boot 3 + Spring Data JPA + H2 内存数据库（可根据需要替换成 MySQL），并通过 `spring.cloud.nacos.discovery` 接入注册中心。
- `support-service` 通过 `RestTemplate + 服务发现` 调用 `account-service`，展示跨服务 HTTP 调用。
- `gateway-service` 基于 Spring Cloud Gateway 暴露统一 API，路由配置见 `backend/gateway-service/src/main/resources/application.yaml`。

#### 基础依赖

1. **JDK 17**（脚本中附带了 Maven Wrapper，可直接使用 `./mvnw` / `mvnw.cmd`）。
2. **Nacos Server**（可本地启动）：

   ```bash
   docker run -d --name nacos -e MODE=standalone -p 8848:8848 nacos/nacos-server:v2.3.0
   ```

3. 如需真实数据库，将各 `application.yaml` 中的 H2 配置替换为自己的数据库连接。

#### 启动顺序

```bash
cd backend
# 只需下载一次依赖
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl gateway-service -am install -DskipTests

# 独立终端分别运行
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl account-service spring-boot:run
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl billing-service spring-boot:run
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl support-service spring-boot:run
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl gateway-service spring-boot:run
```

服务默认端口：

| 服务 | 端口 | 主要职责 |
| --- | --- | --- |
| account-service | 8081 | 线上开户、客户档案、对外暴露客户查询接口 |
| billing-service | 8082 | 账单查询、缴费操作 |
| support-service | 8083 | 投诉、维修工单，内部调用 account-service 校验客户 |
| gateway-service | 8080 | 前端统一入口，负责路由与跨域 |

#### 接口测试

- `docs/api-contracts.md`：列出了所有对内/对外接口、请求参数、返回值与提供/消费方。
- `docs/postman-collection.json`：可直接导入 Postman。
- 支持跨服务联动：提交投诉/维修时会自动向 `account-service` 发起 HTTP GET 验证手机号，若未开户将返回 400。

### 数据库隔离

目前每个服务默认使用 H2 内存库以便快速演示：

| 服务 | H2 URL | 控制台 |
| --- | --- | --- |
| account-service | `jdbc:h2:mem:account-db` | `http://localhost:8081/h2-console` |
| billing-service | `jdbc:h2:mem:billing-db` | `http://localhost:8082/h2-console` |
| support-service | `jdbc:h2:mem:support-db` | `http://localhost:8083/h2-console` |

在生产环境中，只需把对应的 `spring.datasource.*` 配置改成各自独立的 MySQL/PostgreSQL 即可。

### 常见命令

```bash
# 后端聚合构建（跳过测试，下载依赖）
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw clean package -DskipTests

# 某个服务的快速编译
JAVA_HOME=$HOME/.jdks/jdk-17.0.13+11 ./mvnw -pl support-service -am test -DskipTests
```

### 进一步扩展

- 接入消息队列（RocketMQ）进行工单异步通知。
- 在账户/计费服务中接入真实数据库以及 MyBatis-Plus 或 JPA 映射。
- 支持短信/邮件提醒（可新增 notification-service 模块）。
- 在前端补充权限控制（如员工/客户两种角色）。

> 小贴士：为了保证在没有全局 Maven/Java 环境的机器上也可以运行，本仓库已经提供 Maven Wrapper + 手动下载的 JDK 17，用 `JAVA_HOME` 环境变量即可启动。
