# mapjfx

mapjfx provides a JavaFX8 region containing a map. it uses [OpenLayers](http://openlayers.org) as the map technology.
More Information about the project can be found at [the sothawo website](http://www.sothawo.com/projects/mapjfx/).

## license

 Copyright 2014 Peter-Josef Meisch (pj.meisch@sothawo.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

## building the library

this project is built using mvn. The library jar is created by running `mvn package`

## running the showcase

the project contains a showcase example. The source is found in the test sources (src/test/java). It is compiled and
executed with the following command (no need to build the library first):

`mvn test exec:java -Dexec.mainClass="com.sothawo.mapjfx.Showcase" -Dexec.classpathScope=test`

## version history

### 1.1.1-SNAPSHOT

current development version

### 1.1.0

* removed unnecessary code
* reworked MapView constructor to enable SceneBuilder compatibility
* removed slf4j-api dependency and switched to java logging to enable SceneBuilder compatibility

### 1.0.2

* readme correction

### 1.0.1

* javadoc corrections

### 1.0.0

#### new features:

* display a map in a JavaFX8 region
* center of map is provided as JavaFX property
* zoom of map is provided as JavaFX property
* optional animation of center and zoom changes when the properties are changed


