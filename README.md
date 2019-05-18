# heroku-addons &nbsp;[![Build Status](https://travis-ci.com/pine/heroku-addons.svg?branch=master)](https://travis-ci.com/pine/heroku-addons) [![codecov](https://codecov.io/gh/pine/heroku-addons/branch/master/graph/badge.svg)](https://codecov.io/gh/pine/heroku-addons)


## Getting started

```gradle
repositories {
    maven { url 'https://dl.bintray.com/pinemz/maven/' }
}

depepdencies {
    implementation 'moe.pine:heroku-addons:0.1.0'
}
```

## Usage

```java
import moe.pine.heroku.addons.HerokuRedis;

if (HerokuRedis.isDetected()) {
    System.out.println("Host     : " + HerokuRedis.getHost());
    System.out.println("Password : " + HerokuRedis.getPassword());
    System.out.println("Port     : " + HerokuRedis.getPort());    
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
