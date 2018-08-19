# kay-arch
Kay Arch Lib

usage : 

in root project build.gradle
```
allprojects {
    repositories {
    // other repositories
    maven {
            url  "https://kay.bintray.com/KayArch"
        }
    }
```

## Android MVVM Core
add this into your module build.gradle
```
implementation 'kay.arch:xcore:1.8'
```

## Objectbox
A simple helper for objectbox
```
implementation 'kay.arch:box:1.4'
```

## ErrorHandler 
Already included in xcore. But if you want to customize, it's just a piece of cake.
```
implementation "kay.arch:errorhandler:1.1"
```
