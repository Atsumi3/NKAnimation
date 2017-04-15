# NKAnimation  
![Release](https://jitpack.io/v/Atsumi3/NKAnimation.svg)  
プログラミングはじめたての友人が楽しめるように作成したもの

## 注意  
凝ったことは何もしてない

## 使用方法
jitpackを使っています  
Root の build.gradle に以下を追加して、
``` java
allprojects {
  repositories {
  ...
   maven { url 'https://jitpack.io' }
  }
}
```

Projectのbuild.gradle に以下を追加してください
``` java
dependencies {
  ...
  compile 'com.github.Atsumi3:NKAnimation:$latestVersion'
}
```
