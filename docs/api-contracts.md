# 微服务接口协议

| 接口名称 | HTTP 方法 & Path | 提供方 | 消费方 | 请求参数/体 | 响应字段 |
| --- | --- | --- | --- | --- | --- |
| 开户申请 | `POST /account-service/accounts` | account-service | Web 前端、运营人员 | Body：`customerName`、`idNumber`、`phoneNumber`、`planType` | `id`、`customerName`、`phoneNumber`、`planType`、`createdAt` |
| 查询客户资料 | `GET /account-service/accounts/{phone}` | account-service | support-service、Web 前端 | Path：`phone` 手机号码 | 同开户响应 |
| 新建账单 | `POST /billing-service/invoices` | billing-service | 计费后台脚本/测试 | Body：`phoneNumber`、`period`(YYYY-MM) 、`amount` | `id`、`status` 等 |
| 查询账单 | `GET /billing-service/invoices?phoneNumber=` | billing-service | Web 前端、客服坐席 | Query：`phoneNumber` | 列表元素包含 `id`、`period`、`amount`、`status` |
| 支付账单 | `POST /billing-service/invoices/{id}/pay` | billing-service | Web 前端、自助终端 | Path：`id` | 更新后的账单信息 |
| 提交维修单 | `POST /support-service/repairs` | support-service | Web 前端、营业厅工单系统 | Body：`phoneNumber`、`deviceType`、`description`（内部会调用 account-service 校验手机号） | `id`、`type=REPAIR`、`status`、`createdAt` |
| 提交投诉单 | `POST /support-service/complaints` | support-service | Web 前端、客服 IVR | Body：`phoneNumber`、`category`、`detail`（内部会调用 account-service 校验手机号） | 同维修单响应 |
| 查询客户服务单 | `GET /support-service/tickets?phoneNumber=` | support-service | Web 前端、客服坐席 | Query：`phoneNumber` | 列表元素包含 `id`、`type`、`detail`、`status`、`createdAt` |

所有接口均通过 API Gateway 对外统一暴露，外部系统只需访问 `http://<gateway-host>:8080` 并携带模块前缀（如 `/account-service/**`）。内部服务之间（如 support-service 校验帐号）直接使用服务名通过 HTTP 调用。Postman 导入示例位于 `docs/postman-collection.json`（可选，待扩展）。
