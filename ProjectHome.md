# Dr.com for Android #
这个主页是Dr.com的Android的客户端版。Chrome版下面链接。
### Chrome版 ###
http://code.google.com/p/zghaia-drcom-extension/
## 简单介绍 ##
基于post，get方式实现校园网的Dr.com拨号，不过这是专门为Android开发的。

鉴于当前Android智能机和平板电脑盛行，但却无法进行校园网拨号，单靠手机网络，非常耗流量。

所以有此Dr.com拨号器的Android版出现。

## 原理导图 ##
http://zghaia-drcom-android.googlecode.com/files/net6.PNG

一般，拨号都是在是在windows 、Linux和 mac OS X这样的操作系统下完成，

因为这些系统有可以用的dr.com拨号器，如果你找不到可用的，可以看我的

**chrome插件dr.com拨号器**，http://code.google.com/p/zghaia-drcom-extension/

只要能用Chrome的操作系统，都可以拨号了，换言之，chrome跨多少平台，dr.com就跨多少平台。

回归正题，但现在智能机和平板电脑都不带有线网口，而且不能使用Chrome。所以要接入网络，

一、要不就用手机网络，但流量和网速慢

二、要不就是如图中，带无线网卡的笔记本经有线交换机连接，拨号上网后，建立无线wifi网络。
这样智能机或者平板终端通过无线接入，间接上网，这样上网就得依赖身边有带无线网卡的机器。

三、那么能不能去掉笔记本之类的带操作系统来拨号所开的无线呢，这就是图中右侧的无线AP功能
注意这里的无线AP是单纯的无线AP，不带DHCP那种，其实就是无线交换机，不是学校禁止的无线路由哦。
一端接入有线交换机，另一端就产生无线网络。
但问题来了，下一步用智能机和平板电脑接入，如果不是校园网络，那一切就可以上网冲浪了。
问题就在于需要拨号，可是智能机和平板电脑没有对应版本的拨号器啊
这也就是本文开发的Android版Dr.com拨号器的原因，有了它就可以从此只带着智能机。
据说你还得带一个小型化的无线AP，那么随时可以高速接入网络，而无需为低网速头疼和享受低廉的网费。

## 使用帮助 ##
http://code.google.com/p/zghaia-drcom-android/w/list

## 关于代码 ##
http://code.google.com/p/zghaia-drcom-android/source/browse/#svn%2Ftrunk

## 问题反馈 ##
http://code.google.com/p/zghaia-drcom-android/issues/list

## 效果截图 ##
http://zghaia-drcom-android.googlecode.com/files/main.PNG
http://zghaia-drcom-android.googlecode.com/files/setting.PNG
http://zghaia-drcom-android.googlecode.com/files/about.PNG
http://zghaia-drcom-android.googlecode.com/files/bar.PNG
http://zghaia-drcom-android.googlecode.com/files/notif.PNG

## 更新历史 ##
v1.0算是个最初测试版，但由于编译依赖过高，安装出错，所以被抛弃

v1.1还是如上面，没有解决

v1.2正式可以使用了，但还需完善

v1.2.1则处理了图标

v1.3将加入通知栏功能