这是把语音识别服务的<离在线融合SDK>打包为aar的
离在线融合SDK
版本：2.0.17
发布时间：2015-7-6

由于使用sdk的话，还有要包一些资源文件加入到我们的项目中，所以自己打包一下他们的资源文件，我们把aar加入
到我们的项目就可以了。
aar里面包含了：
1.识别库的so文件
2.识别库的jar文件
3.图片、文字资源
4.Constant定义的字段

使用的方法：
1.把aar引用于自己的工程
2.拷贝bdspeech_digital_layout.xml
3.启动"com.baidu.action.RECOGNIZE_SPEECH"
4.加入activity声明和Key
5.或者参考sdk的代码使用