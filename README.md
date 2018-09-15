# Playa
略显粗糙的玩Android APP，玩Android网站：http://www.wanandroid.com/

## Features
大概大概地做了一下，已实现的功能如下：
* 首页，包含顶部轮播图和数据列表
* 项目
* 体系
* 导航
* 帐号登录
* 搜索，包含热搜和搜索历史

部分地方没有做，例如设置页只是一个页面，实际的代码并没有写，设置页面的实现可参考：https://github.com/zeleven/mua

## Dependencies
* [Butter Knife](https://github.com/JakeWharton/butterknife)
* [RxJava](https://github.com/ReactiveX/RxJava) （异步）
* [Retrofit](https://github.com/square/retrofit) （网络请求）
* [Dagger 2](https://github.com/google/dagger) （依赖注入）
* [Glide](https://github.com/bumptech/glide) （图片加载）
* [CircleImageView](https://github.com/hdodenhof/CircleImageView) （圆形图片）
* [FlexboxLayout](https://github.com/google/flexbox-layout) （Google 的 Flex 布局）
* [VerticalTabLayout](https://github.com/qstumn/VerticalTabLayout) （垂直导航条）
* [Banner](https://github.com/youth5201314/banner) （轮播图）
* [EventBus](https://github.com/greenrobot/EventBus)
* [Realm](https://realm.io/) （数据库）

## Structure
代码组织如下：
* `app/src/main/java/io/github/zeleven/playa/data`
	* `/model/`，数据模型类
	* `/source/`，数据操作类
* `app/src/main/java/io/github/zeleven/playa/di`，依赖注入类
* `app/src/main/java/io/github/zeleven/playa/ui`
	* `/adapter`，Adapter 类
	* `/base`，Base类
	* `/listener`，listener类
	* `/module`，主要的功能模块
	* `/widget`，自定义Widget
* `app/src/main/java/io/github/zeleven/playa/utils`，一些工具类

## Screenshots
|启动图|首页|项目|
|:-:|:-:|:-:|
|![](/screenshots/S80915-154646.jpg)|![](/screenshots/S80915-154418.jpg)|![](/screenshots/S80915-154423.jpg)|

|体系|导航|我的|
|:-:|:-:|:-:|
|![](/screenshots/S80915-154428.jpg)|![](/screenshots/S80915-154434.jpg)|![](/screenshots/S80915-154438.jpg)|

|详情页|登录|搜索|
|:-:|:-:|:-:|
|![](/screenshots/S80915-154519.jpg)|![](/screenshots/S80915-154700.jpg)|![](/screenshots/S80915-154501.jpg)|

## Author
* [微博](https://weibo.com/u/6659463044)
* QQ群：779308363

## License
MIT协议，详细内容请查看[LICENSE文件](/LICENSE)
