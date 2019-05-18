# heroku-addons &nbsp;[![Build Status](https://travis-ci.com/pine/heroku-addons.svg?branch=master)](https://travis-ci.com/pine/heroku-addons)

## Getting started

```gradle
repositories {
    maven { url 'https://dl.bintray.com/pinemz/maven/' }
}

depepdencies {
    implementation 'moe.pine:heroku-addons:0.1.0'
}
```

```java
import moe.pine.heroku.addons.HerokuRedis;

if (HerokuRedis.isDetected()) {
    System.out.println("Host     : " + HerokuRedis.getHost());
    System.out.println("Password : " + HerokuRedis.getPassword());
    System.out.println("Port     : " + HerokuRedis.getPort());    
}
```

## License
MIT &copy; [Pine Mizune](https://profile.pine.moe)
