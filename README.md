# TTWidget: Simple Android widget

[![](https://jitpack.io/v/FerrandTian/TTWidget.svg)](https://jitpack.io/#FerrandTian/TTWidget)

Simple Android widget

### Preview

![Preview_1](https://github.com/FerrandTian/TTWidget/raw/main/Screenshot.jpg)

### Gradle settings

If you don't have this already, add it to your **root** build.gradle file:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then you can add the dependency to your **app** build.gradle file:
```
dependencies {
    ...
    implementation 'com.github.FerrandTian:TTWidget:1.0.1'
}
```

### Layout

```xml
<tt.widget.NumberPicker
    android:layout_width="48dp"
    android:layout_height="144dp"
    app:inputEnabled="false"
    app:selectionDivider="@drawable/selector_teal_gray"
    app:selectionDividerHeight="1px"
    app:selectionDividersDistance="28dp"
    app:selectorWheelItemCount="5"
    app:textColor="@color/teal_gray"
    app:textSize="18sp"
    app:wrapSelectorWheel="false" />
```

## LICENSE

    Copyright (C) 2022 TianFeng
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
