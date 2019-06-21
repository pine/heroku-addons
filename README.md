# heroku-addons
[![Build Status](https://travis-ci.com/pine/heroku-addons.svg?branch=master)](https://travis-ci.com/pine/heroku-addons)
[![codecov](https://codecov.io/gh/pine/heroku-addons/branch/master/graph/badge.svg)](https://codecov.io/gh/pine/heroku-addons)
[![Download](https://api.bintray.com/packages/pinemz/maven/heroku-addons/images/download.svg)](https://bintray.com/pinemz/maven/heroku-addons)

> :mag: Heroku add-ons utilities.

![](images/resized.jpg)<br>
<sup><sup>&copy; Leung Cho Pan/123RF.COM</sup></sup>
<br>
<br>

## Getting started
The library is published to [Bintray](https://bintray.com/pinemz/maven/heroku-addons).

```gradle
repositories {
    jcenter()
}

depepdencies {
    implementation 'moe.pine:heroku-addons:0.2.2'
}
```

## Supported add-ons
Don' you find what you need? We look forward to your contribution :smile:

- [Heroku Redis](https://elements.heroku.com/addons/heroku-redis)
- [JawsDB MySQL](https://elements.heroku.com/addons/jawsdb)

## Usage

```java
import moe.pine.heroku.addons.HerokuRedis;

final HerokuRedis redis = HerokuRedis.get();
if (redis != null) {
    System.out.println("Host     : " + redis.getHost());
    System.out.println("Password : " + redis.getPassword());
    System.out.println("Port     : " + redis.getPort());
}
```

## Development
### Test

```
$ ./gradlew clean test
```

### Upload Bintray

```
$ export BINTRAY_USER=username
$ export BINTRAY_KEY=apiKey
$ ./gradlew clean assemble bintrayUpload
```


## License
MIT &copy; [Pine Mizune](https://profile.pine.moe)
