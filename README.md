# cas-wjxSSO
作为问卷星与CAS认证中间代理层，实现通过CAS认证登录问卷星用户体系
# 参数修改
请修改resource下面application.properties内的参数。相关参数可以根据自己实际情况配置  
cas.server-url=你的CAS认证地址  
app.url=你部署或测试的服务器ip:端口  
server.port = 服务运行的端口

# 问卷星sso参数
appId1=问卷星用户体系下获取  
appKey1=联系问卷星客服提供，该接口收费  
ssoUrl1=点击发布问卷是获取  
ssou2=用户的id，点发布问卷时会包含  
ssouserSystem=用户体系id  
