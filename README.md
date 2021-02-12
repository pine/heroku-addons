# heroku-addons
[![Build Status](https://travis-ci.com/pine/heroku-addons.svg?branch=master)](https://travis-ci.com/pine/heroku-addons)
[![codecov](https://codecov.io/gh/pine/heroku-addons/branch/master/graph/badge.svg)](https://codecov.io/gh/pine/heroku-addons)
[![Download](https://api.bintray.com/packages/pinemz/maven/heroku-addons/images/download.svg)](https://bintray.com/pinemz/maven/heroku-addons)

> :mag: Heroku add-ons utilities.

![](images/resized.jpg)<br>
<sup><sup>&copy; Leung Cho Pan/123RF.COM</sup></sup>
<br>
<br>

## Requirements

- Java 8 or later
  - Android Java has not been supported, because the add-on is for server apps on Heroku

## Getting started
The library is published to [Maven Central](https://search.maven.org/). Please replace `$latest_version` below with the actual version.

```gradle
repositories {
    mavenCentral()
}

depepdencies {
    implementation "moe.pine:heroku-addons:$latest_version"
}
```

### Notes on the old versions
The old versions `< 0.3.2` had been published to jcenter, but jcenter is scheduled to end.

Please use the newer versions  that  are published to Maven Central.

## Supported add-ons
Don' you find what you need? We look forward to your contribution :smile:

- [Heroku Redis](https://elements.heroku.com/addons/heroku-redis)
- [JawsDB MySQL](https://elements.heroku.com/addons/jawsdb)
- [JawsDB Maria](https://elements.heroku.com/addons/jawsdb-maria)

## Usage
If you want to use [Heroku Redis](https://elements.heroku.com/addons/heroku-redis) add-on, please use the code as following.

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

### Upload Maven Central

```
$ ./gradlew clean publish
```


## License
MIT &copy; [Pine Mizune](https://profile.pine.moe)
